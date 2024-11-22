package SafetyNet.Alerts.repository;

import SafetyNet.Alerts.model.DataList;
import com.jsoniter.JsonIterator;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class ReadData {

    private final DataList data;

    public ReadData() throws IOException {
        String temp = getFromResource( "list.json");
        this.data = JsonIterator.deserialize(temp, DataList.class);
    }

    private String getFromResource(String s) throws IOException {
        InputStream is = new ClassPathResource(s).getInputStream();
        return IOUtils.toString(is, StandardCharsets.UTF_8);
    }

    public DataList getData() {
        return data;
    }

}
