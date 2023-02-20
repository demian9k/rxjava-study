package io.demian.rxjava.schedulers.appliedExample;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class weatherFile {

//    private static final String URL = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
//Observable<String> urlSource = Observable.just(URL)
    //                .map(OkHttpHelper::getWithLog)
//                .subscribeOn( Schedulers.io() );

    public void run() throws IOException, URISyntaxException {
//        File file = getFileFromResources("weatherdata.json");

//        System.out.println( String.valueOf((char) new FileReader(file).read ));


        String s = getFileStringFromResources("weatherdata.json");


        Observable<String> source = Observable.just(s)
                .subscribeOn( Schedulers.io() );

        Observable<String> temp = source.map( this::parseTemperature );
        Observable<String> city = source.map( this::parseCityName );
        Observable<String> country = source.map( this::parseCountry );

        CommonUtils.exampleStart();

        /**
         * CONCAT
         */
        Observable<String> concatenatedSource = Observable
                .concat( temp, city, country )
                .observeOn(Schedulers.newThread());
        /**
         * subscribe
         */
        concatenatedSource.subscribe(Log::it);
//
        CommonUtils.sleep(1000);

    }

    private String parseTemperature(String json) {
        return parse(json, "\"temp\":[0-9]*.[0-9]*");
    }

    private String parseCityName(String json) {
        return parse(json, "\"name\": \"[a-zA-Z]*\"");
    }

    private String parseCountry(String json) {
        return parse(json, "\"country\": \"[a-zA-Z]*\"");
    }


    public String parse(String json, String regex) {

        Pattern pattern = Pattern.compile(regex);

        Matcher match = pattern.matcher(json);

        if( match.find() ) {
            return match.group();
        }

        return "";
    }

    private String getFileStringFromResources(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return readLineByLineJava8(resource.toURI());
        }

    }

    private String[] readFileAsLineArray(URI filePath) {
        String[] arr = new String[0];

        int idx = 0;

        try {
            Object[] objectArray = Files.lines(Paths.get(filePath)).toArray();
            arr = Arrays.copyOf(objectArray, objectArray.length, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }

    private String readLineByLineJava8(URI filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static void main(String[] args) {
        weatherFile demo = new weatherFile();
        try {
            demo.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



