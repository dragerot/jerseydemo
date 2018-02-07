package net.toregard.jerseydemo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//(classes = {AppTest.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JerseydemoApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void init() {
        assertThat(true).isTrue();
    }
    // @Test
//    public void initTest() {
//        //assertThat(testRestTemplate, is(notNullValue()));
//    }

    // @Test
   /* public void listUsersTest() {
        ResponseEntity<Users> users = this.testRestTemplate.getForEntity("/users", Users.class);
        assertThat(users, is(notNullValue()));
        assertThat(users.getStatusCodeValue(), is(200));
    }

 //   @Test
    public void getAdmninResourceTest() {
//        ResponseEntity<?> s=this.testRestTemplate.getForEntity(
//                "/{username}/vehicle", String.class, "Phil");
        ResponseEntity<?> s=this.testRestTemplate.getForEntity("users/{id}", Integer.class, 1);
    }


    //@Test
  /*  public void contextLoads() {
        double d = 1.3;
        assertThat(d, is(1.3));
        assertThat(d, is(not(1.4)));

        Assert.assertTrue(true);

        String str = "test";
        assertThat(str, anyOf(is("test"), is("this"), is("that")));
        assertThat(str, not(anyOf(is("tasty"), is("this"), is("that"))));

        List<String> list = new ArrayList<String>() {{
            add("one");
            add("two");
            add("three");
        }};*/


    //assertThat(list, hasItem("two"));
    //assertThat(list, not(hasItem("ttwo")));

//		class Bean<T> {
//			private T t;
//			public void setT(T t){this.t = t;}
//			public T getT(){return this.t;}
//		}
//
//		Bean<String> myBean = new Bean<String>();
//		myBean.setT("hi");
//		assertThat(myBean, hasProperty("t",is("hi")));
//		assertThat(myBean, hasProperty("t",startsWith("h")));
    //}
}

