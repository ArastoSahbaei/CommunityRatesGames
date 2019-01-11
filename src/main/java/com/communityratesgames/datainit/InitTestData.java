package com.communityratesgames.datainit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

public class InitTestData {

    public void derp() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("testdata.json");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(is));
            Set<Map<String, Object>> dataHolders = mapper.readValue(
                    is,
                    new TypeReference<Set<Map<String, Object>>>() {}
            );
            for (Object o : dataHolders
                 ) {
                System.out.println(o.toString());
            }
        }catch(IOException e) {
            System.out.println("ERROL");
        }
    }
}
