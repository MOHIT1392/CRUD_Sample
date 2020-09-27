package in.mohit.springboot.crudapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import in.mohit.springboot.crudapi.model.Employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testSuccessUserCreation() throws Exception {

        //Arrange
        Employee employee = new Employee();
        employee.setDepartment("Dept25");
        employee.setGender("Male1");
        employee.setName("Mohit1");


        //Act and Assert
        mockMvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonBytes(employee))).
                andDo(print()).andExpect(status().isOk());


    }


    @Test
    public void testInputValidation() throws Exception {

        //Act and Assert
        mockMvc.perform(get("/api/employee/{id}", 3L).
                contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(jsonPath("$.name", is("mohit")));
    }

    @Test
    public void testInputValidation1() throws Exception {

        mockMvc.perform(get("api/employee").contentType(MediaType.APPLICATION_JSON_VALUE)).
                andDo(print()).andExpect(status().isOk());

    }


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }


}