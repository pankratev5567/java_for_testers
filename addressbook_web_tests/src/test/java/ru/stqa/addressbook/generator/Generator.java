package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import model.ContactData;
import ru.stqa.common.CommonFunctions;
import model.GroupData;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.xml.XmlMapper;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;
    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }
    private void run() throws IOException {
        var data = generate();
        save(data);
    }
    private Object generate() {
        if ("groups".equals(type)) {
            return generatorGroups();
        } else if ("contacts".equals(type)) {
            return generatorContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных" + type);
        }
    }
    private Object generateData(Supplier<Object> dataSupplier) {
        Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
        var result = new ArrayList<Object>();
        for (int i=0;i<count;i++) {
            result.add(dataSupplier.get());
        }
        return result;
    }
    private Object generatorGroups() {
        return generateData(()-> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10)));
    }






    private Object generatorContacts() {
        return generateData(() -> new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10)));
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output);) {
                writer.write(json);
            }
        } else if ("yaml".equals(format)){
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)){
                var mapper = new XmlMapper();
                mapper.writeValue(new File(output), data);
        }
        else{
            throw new IllegalArgumentException("Неизвестный формат" + format);
        }
        }
    }
