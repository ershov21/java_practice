public class App {
    public static void main(String[] args) throws Exception {
        String year = "2021", month = "01", day = "23";
        RequestInfo request = new RequestInfo();
        request.makeRequest(year, month, day);
    }
}
