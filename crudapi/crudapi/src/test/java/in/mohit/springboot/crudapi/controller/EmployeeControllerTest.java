package in.mohit.springboot.crudapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.mohit.springboot.crudapi.controller.EmployeeController;
import in.mohit.springboot.crudapi.model.Employee;
import in.mohit.springboot.crudapi.service.EmployeeService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;


    @Test
    public void testSaveSuccess() throws IOException, Exception {
    	
    	Employee employee = new Employee();
    	employee.setId(9);

    	employee.setDepartment("Dept25");
    	employee.setGender("Male1");
    	employee.setName("Mohit1");
 
    	
    
    	
    	//given(service.save(employee)).willReturn(employee);
    	
    	Mockito.when(service.save(employee)).thenReturn(new Employee());
    	
    	 mvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON_VALUE)
                 .content(convertObjectToJsonBytes(employee))).
                 andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testInputValidation() throws Exception {
       List<Employee> actualList=new ArrayList<>();
   	Employee employee = new Employee();
   	employee.setId(9);
	employee.setDepartment("Dept25");
	employee.setGender("Male1");
	employee.setName("Surbhi1");
	actualList.add(employee);
    	Mockito.when(service.get()).thenReturn(actualList);
        //Act and Assert
        mvc.perform(get("/api/employee").
                contentType(MediaType.APPLICATION_JSON_VALUE)).
        andDo(print()).andExpect(status().isOk());
    }
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }


}
