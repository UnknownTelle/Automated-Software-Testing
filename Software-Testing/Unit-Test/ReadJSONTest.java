package com.UnitTest;
import com.Mock.ReadJSONMock;
import com.ReadJSON;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class ReadJSONTest {

    @Test
    public void testReadJSONIsReadingFileCorrectly() throws IOException {
        String mockFile = ReadJSONMock.getAPI();
        String originalFile = ReadJSON.getAPI("restaurant-data.json");
        assertEquals(mockFile, originalFile);
    }
}
