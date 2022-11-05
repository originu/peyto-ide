package ${packageName};

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 
 * @author 
 *
 */
@AutoConfigureMockMvc
@SpringBootTest
class ${className}Test {

    @Autowired
    private ${className} dao;

    @Test
    void test() throws Exception {
        List<${returnType}> items = dao.${selectId}();
        for( ${returnType} item : items ) {
            System.out.println(item);
        }
    }
}
