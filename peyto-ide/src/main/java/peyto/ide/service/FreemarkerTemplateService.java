package peyto.ide.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;

@Service
public class FreemarkerTemplateService implements TemplateService {

    private Configuration cfg;

    @PostConstruct
    public void startup() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates" );
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        this.cfg = cfg;
    }

    public void generate(Object dataModel, Writer out) throws IOException, TemplateException {
        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("mybatis/basic_mapper.ftlh");
//        Template temp = cfg.getTemplate("test.ftlh");
        /* Merge data-model with template */
        temp.process(dataModel, out);
    }
}

