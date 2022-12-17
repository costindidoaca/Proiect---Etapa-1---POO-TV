package iofiles;

public record Action(String type, String page, Credentials credentials, String feature,
                     String startsWith, Filters filters, String count, String movie, int rate) {
}
