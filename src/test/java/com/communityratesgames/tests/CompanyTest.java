package com.communityratesgames.tests;

import com.communityratesgames.archive.Shrink;
import com.communityratesgames.domain.Company;
import com.communityratesgames.transactions.CompanyDataAccess;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RunWith(Arquillian.class)
public class CompanyTest {

    @Inject
    private CompanyDataAccess companyDataAccess;

    @Deployment
    public static WebArchive createDeployment() {
        return Shrink.createDeployment();
    }

    private String[] companies() {
        String companies[] = new String[]{
         "id Software", "Microsoft",
         "Valve", "Nintendo", "Sony", "Sega", "Google", "Apple",
         "Blizzard Entertainment", "Activision", "Rockstar", "EA", "Bandai Namco",
         "Ubisoft Entertainment", "Square Enix", "BlueByte", "Konami", "Capcom",
         "Bungee", "Microsoft", "Epic Games", "Paradox", "DICE"
        };

        return companies;
    }

    @Test @Ignore
    public void thereIsCompanies() {
        final List<Company> result = companyDataAccess.showAllCompanies();
        final String[] companies = companies();

        String companiesFromBackend[] = new String[23];

        for ( int i = 0; i < result.size(); i++) {
            companiesFromBackend[i] = result.get(i).getCompanyName();
        }

        Arrays.sort(companies);
        Arrays.sort(companiesFromBackend);

        Assert.assertArrayEquals(companies, companiesFromBackend);
    }

    @Test @Ignore
    public void getACompany() {
        final Company company = companyDataAccess.findCompanyByCompanyName("Sega");
        Assert.assertEquals("Sega", company.getCompanyName());

    }
}
