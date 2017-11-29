package net.toregard.jerseydemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
        //(classes = {AppTest.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeployRestTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

   // @Test
    public void initTest() {
        assertThat(testRestTemplate, is(notNullValue()));
    }

    //@Test
    public void listUsersTest() {
        ResponseEntity<?> response = this.testRestTemplate.getForEntity("/deploy", String.class);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCodeValue(), is(200));
    }
}
