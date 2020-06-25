package mvp.tinder.helper;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;

@Log4j2
public class TemplateEngine {

    private final Configuration config;

    public TemplateEngine(final String path) throws IOException {
        this.config = new Configuration(Configuration.VERSION_2_3_28) {{
            setDirectoryForTemplateLoading(new File(path));
            setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
            setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            setLogTemplateExceptions(false);
            setWrapUncheckedExceptions(true);
        }};
    }

    public static TemplateEngine resources(final String path_from_project_resources) throws IOException, URISyntaxException {
        String path = Paths
                .get(TemplateEngine.class.getResource(path_from_project_resources).toURI())
                .toFile().getAbsolutePath();
        return new TemplateEngine(path);
    }

    public void render(String templateFile, HashMap<String, Object> data, HttpServletResponse resp) {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        try (PrintWriter w = resp.getWriter()) {
            config.getTemplate(templateFile).process(data, w);
        } catch (TemplateException | IOException x) {
            log.error("Freemarker error");
            throw new RuntimeException();
        }
    }
}
