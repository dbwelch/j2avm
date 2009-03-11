package test;

import java.io.IOException;

import org.epistem.server.http.HTTPRequest;
import org.epistem.server.http.handlers.annotations.ParamName;
import org.epistem.server.http.handlers.annotations.PathRegex;

public class TestPojo {

    @PathRegex( "foo" ) 
    public String foo( HTTPRequest request ) throws IOException {
        return "Hello Foo";
    }

    @PathRegex( "bar" ) 
    public String foo( @ParamName("name") String name ) throws IOException {
        return "Hello " + name;
    }

}
