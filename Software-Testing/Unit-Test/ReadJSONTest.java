package com.UnitTest;


import com.Mock.ReadJSONMock;
import com.ReadJSON;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class ReadJSONTest {

    @Test
    public void getAPIIfFileReadsInCorrectly() throws IOException { //name it better
        String mockFile = ReadJSONMock.getAPI();
        String onlineFile = ReadJSON.getAPI("restaurant-data.json");
        assertEquals(mockFile, onlineFile);
    }

}
