/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package finalprojectB;

import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

    private boolean printStatus = false;
    private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

    public UrlValidatorTest(String testName) {
        super(testName);
    }



    public void testManualTest()
    {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        //System.out.println(urlVal.isValid("http://www.amazon.com"));
        assertTrue(urlVal.isValid("http://www.amazon.com/"));
        assertTrue(urlVal.isValid("http://www.google.com/"));
        assertTrue(urlVal.isValid("http://www.apache.org/"));
        assertFalse(urlVal.isValid("1.1.1.1")); //changed
        assertFalse(urlVal.isValid("www.amazon.com/x/y/z")); //changed
        assertTrue("Port 8000 should be valid", urlVal.isValid("http://www.amazon.com:8000/abcde")); //changed
        assertTrue("Query String \"?x=y&y=z\" should be valid", urlVal.isValid("http://www.amazon.com/abcde?x=y&y=z")); //changed
        assertFalse("\"3ft\" shouldn't be a vlid protocol", urlVal.isValid("3ft://google.com"));
        assertFalse("Query String \"?x=!\" should be invalid", urlVal.isValid("http://www.amazon.com/abcde?x=!"));
        assertFalse("No digit in an ip address should be above 255", urlVal.isValid("http://1.2.3.400/")); //changed


    }


    public void testYourFirstPartition()
    {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

        //Min & max ports
        assertTrue(urlVal.isValid("https://www.google.com:0/1/2/3/4"));
        assertTrue(urlVal.isValid("http://6.5.4.3:65535/?h=j")); //changed

        //Min & max IP's
        assertTrue(urlVal.isValid("http://0.0.0.0"));
        assertTrue(urlVal.isValid("http://255.255.255.255"));


    }

    public void testYourSecondPartition(){
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

        //Min & max ports
        assertFalse(urlVal.isValid("http://www.google.com:-1/1/2"));
        assertFalse(urlVal.isValid("http://6.5.4.3:65536/?h=j"));

        //Invalid IP's
        assertFalse(urlVal.isValid("http://211.-2.30.174"));
        assertFalse(urlVal.isValid("http://256.1.2.3")); //changed
    }


    public void testIsValid()
    {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

        String myScheme[] = {
                "http://",
                "ftp://",
                "https://",
                "sftp://",
                "ssh://",
                ""
        };

        String myAuthority[] = {
                "amazon.com",
                "google.com",
                "apple.com",
                "sky.net",
                "kissanime.to",
                ""
        };
        String myPath[] = {
                "/07/87/2017/index.html",
                "/wassup/homie/index.php",
                ".html",
                ".php",
                "/17/04/1995/NbZ.htm",
                ""
        };


        String first[] = {
                "http://amazon.com",
                "somedomain.org",
                "theguardian.net",
                "ftp://250.19.101.15"
        },
                second[] = {":0",
                        ":10000",
                        ":13000",
                        ":47027",
                        ":65535",
                        ""
                },
                third[] = {
                        "/somePage/fire/index.php",
                        "/this=that",
                        "/a=b&b=c",
                        ""
                };
        for (byte i = 0; i < first.length; i++) {
            for (byte j = 0; j < second.length; j++) {
                for (byte k = 0; j < third.length; j++) {
                    assertTrue(first[i] + second[j] + third[k], urlVal.isValid(first[i] + second[j] + third[k]));
                    assertTrue(myScheme[i] + myAuthority[j] + myPath[k], urlVal.isValid(myScheme[i] + myAuthority[j] + myPath[k]));
                }
            }
        }
    }

    public void testAnyOtherUnitTest()
    {

    }
    /**
     * Create set of tests by taking the testUrlXXX arrays and
     * running through all possible permutations of their combinations.
     *
     * @param testObjects Used to create a url.
     */


}

