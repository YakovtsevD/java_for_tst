package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import com.lavasoft.GetIpLocation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
      String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.214.72.39");
      Assert.assertEquals(geoIP, "<GeoIP><Country>BY</Country><State>06</State></GeoIP>");

    }

}
