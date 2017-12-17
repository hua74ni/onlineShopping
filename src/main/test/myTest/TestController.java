package myTest;


import com.biz.platform.web.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext-*.xml","classpath:/spring/springmvc.xml"})
public class TestController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testAddUser() throws Exception{
        User user = new User();
        user.setUserId(null);
        user.setUserCode("test222");
        user.setUserPassword("123");
        user.setUserName("测试");
        user.setUserType("测试人员");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr =  mapper.writeValueAsString(user);
        } catch (IOException e) {
            throw e;
        }

        mockMvc.perform(post("/user/addUser.do")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andDo(print())
                .andExpect(status()
                .isOk());

    }

    @Test
    public void testLoadUser() throws Exception{
        User user = new User();
        user.setUserId("a8a63e8d818c4503a26929bcc3c156fc");

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr =  mapper.writeValueAsString(user);
        } catch (IOException e) {
            throw e;
        }
        System.out.println(jsonStr);

        mockMvc.perform(post("/user/loadUser.do")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andDo(print())
                .andExpect(status()
                        .isOk());

    }

}
