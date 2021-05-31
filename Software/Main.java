package com;

import org.json.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Restaurant[] restaurants;
        restaurants = ReadJSON.convertJson(ReadJSON.getAPI("restaurant-data.json"));
    }
}
