package jwt;

@Component
public class JWTGenerator {


    public String generate(UserModel userModel) {


        Claims claims = Jwts.claims().setSubject(userModel.getUsername());
        claims.put("userId", String.valueOf(userModel.getId()));
        claims.put("role", userModel.getRole());

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "volvo").compact();
    }
}