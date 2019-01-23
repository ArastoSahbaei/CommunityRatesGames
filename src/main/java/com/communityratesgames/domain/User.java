package com.communityratesgames.domain;

import com.communityratesgames.model.UserModel;
import lombok.ToString;
import org.picketlink.idm.model.annotation.Unique;

import com.communityratesgames.util.FileLimitReachedException;
import com.communityratesgames.util.InvalidFileFormatException;
import com.communityratesgames.util.IImageEntity;
import com.communityratesgames.util.ImageUtils;

import javax.persistence.*;
import javax.servlet.ServletContext;
import java.io.*;
import java.sql.Timestamp;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.security.*;
import java.math.BigInteger;
import java.util.Properties;

@Entity
@ToString
@Table(name = "user_entity")
public class User implements Serializable, IImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp userCreated;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    private String password;
    private String role;
    private static String imageDir;
    private static String defaultImage;
    private static Long fileSizeLimit;

    public User(UserModel userModel) {
        this.id = userModel.getId();
        this.userName = userModel.getUsername();
        this.email = userModel.getEmail();
        this.setPassword(userModel.getPassword());
        this.role = userModel.getRole();
        this.userCreated = userModel.getUserCreated();
    }

    public User(String username, String email, String password) {
        this.userCreated = new Timestamp(System.currentTimeMillis());
        this.userName = username;
        this.email = email;
        this.encryptPassword(password);
        this.role = "User";
    }

    public String toJMS() {
        return this.getUserName();
    }

    public Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Timestamp userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public String getPassword() {
        return password;
    }

    public static String hashPassword(String password, String hash) {
        try {
            // Use hashed SHA-256.
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            password += hash;
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] data = md.digest();
            return String.format("%064x", new BigInteger(1, data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptPassword(String password) {
        byte[] hash = new byte[4];
        SecureRandom rand = new SecureRandom();
        rand.setSeed(rand.generateSeed(4));
        rand.nextBytes(hash);
        this.passwordHash = Integer.toHexString(
                ((int)hash[0] << 24) |
                ((int)hash[1] << 16) |
                ((int)hash[2] << 8) |
                (int)hash[3]);

        this.password = hashPassword(password, this.passwordHash);

        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void prepareImageStorage(ServletContext context) throws IOException {
        if (imageDir != null) {
            return;
        }

        Properties props = new Properties();
        props.load(context.getResourceAsStream("/WEB-INF/image.properties"));
        imageDir = props.getProperty("image.directory");
        if (imageDir == null) {
            throw new IOException("image storage path is not set");
        }

        defaultImage = props.getProperty("image.default");

        String limit = props.getProperty("image.maxSize");
        if (limit == null) {
            fileSizeLimit = 512L * 1024L;
        } else {
            try {
                fileSizeLimit = Long.parseLong(limit) * 1024L;
            } catch (NumberFormatException e) {
                fileSizeLimit = 512L * 1024L;
            }
        }
    }

    @Override
    public void storeImage(InputStream data) throws IOException, FileLimitReachedException, InvalidFileFormatException {
        byte[] magic = new byte[4];
        int in;

        if (data == null) {
            try {
                Files.delete(Paths.get(imageDir, this.userName));
            } catch (NoSuchFileException e) {
                // Ignore; this happend when no avatar is set for the user.
            }
            return;
        }

        // This is expected to throw an exception; use this to validate the file format.
        data.read(magic);
        ImageUtils.getFormat(magic);

        // Write to a temporary file, in case something goes wrong during the write.
        File file = new File(Paths.get(imageDir, this.userName + ".tmp").toString());
        DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
        stream.write(magic);
        for (int i = 4; i < fileSizeLimit; i++) {
            // Pass all data from the stream to the file directly, without using RAM.
            // This will prevent out-of-memory on large files.
            in = data.read();
            if (in == -1) {
                stream.close();
                File result = new File(Paths.get(imageDir, this.userName).toString());
                if (!file.renameTo(result)) {
                    throw new IOException("failed to replace old avatar");
                }
                return;
            }

            stream.write(in);
        }

        file.delete();
        throw new FileLimitReachedException("file size limit reached");
    }

    @Override
    public File loadImage() throws IOException {
        File file = new File(Paths.get(imageDir, this.userName).toString());
        if (!file.exists()) {
            if (defaultImage == null) {
                return null;
            } else {
                file = new File(defaultImage);
                if (!file.exists()) {
                    // If no default image exists, return NULL.
                    return null;
                }
            }
        }

        if (!file.canRead()) {
            throw new FileNotFoundException("no read access to file");
        }

        if (file.isDirectory()) {
            throw new FileNotFoundException("cannot open directory");
        }
        return file;
    }
}
