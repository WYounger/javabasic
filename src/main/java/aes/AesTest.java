package aes;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.jupiter.api.Test;

public class AesTest {
    private static final String key1 = "123123123123";
    private static final String key2 = "321321321321";

    @Test
    public void aes(){
        RSA rsa = new RSA("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALSeT2jnKjfgLZYU9WvoUJoh7jG0il1t\n" +
                "q5+W/pE0oOdCpcMGGcBCYzmEtlVEJbyZUsCH524T8x6M0gveHkt7r+8CAwEAAQ==","MIIBpjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQIT/QUE0EzOmYCAggA\n" +
                "MBQGCCqGSIb3DQMHBAj8PK/4FQQiPQSCAWD7C6Pf3c9bogbXx0eHTOMeQWU0B1OB\n" +
                "xtzPQyHOHnDciE6C4oxj1GM+DSfUKSF/0WH5c/jAvSS9DT3UJvM4M0knWy8u1hMg\n" +
                "0jalF07RV9nKaANIcr2uUJt3TZMdRBzQOk/DbrnGPBGvy4Ow4hgDZsjNAOU4Jwid\n" +
                "tL4NmndNoBhc0p9brLbygHVDiaFEQinEncYqlyzoMgF8Awi6Vms4bQzRFbnD8hvQ\n" +
                "OyLScwEC5JHj3fihNxZBDtuMDv8ewHKhZ5zPgcaG2GpDzHx9gBsCjDRaI1/thnV5\n" +
                "Z+b7XF8IN0YBRvL/6fyubVx8l0RvGuv0H0Cs9u9xTFgjW6pKxcVcR4h3XX9UwwOs\n" +
                "BZByQLvdKHWIyEQWxAqPBIX133+nw5cJ30293uM9E5qxVaD2ES3oRaU/DQjnQ+MQ\n" +
                "0Pvsj4EYUazkYahC2AGQod6rPRruXD+TiceP2hB1qJJnCWvBqvfJbUlS");

        String data123 = rsa.encryptHex("data123", KeyType.PublicKey);
        System.out.println(rsa.decryptStr(data123,KeyType.PrivateKey));
    }
}
