// Solution #1: Base 62. map String to String 
// quick read. but string takes more space than integer
public class Codec {
    
    private Map<String, String> longToShortMap = new HashMap<>();
    private Map<String, String> shortToLongMap = new HashMap<>();
    private final String domain = "http://tinyurl.com/";
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(longUrl == null || longUrl.length() == 0) return "";
        if(!longToShortMap.containsKey(longUrl)) {
            String temp = convert10To62(longToShortMap.size());
            longToShortMap.put(longUrl, temp);
            shortToLongMap.put(temp, longUrl);
        }
        return domain + longToShortMap.get(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if(shortUrl == null || shortUrl.length() == 0) return "";
        return shortToLongMap.get(shortUrl.substring(domain.length()));
    }

    private final String encodeLetter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String convert10To62(int num) {
        if(num == 0) return "0";
        char[] chs = encodeLetter.toCharArray();
        String res = "";
        while(num > 0) {
            res += chs[num % 62];
            num /= 62;
        }
        return res;
    }
}
// Solution #2L Base 62. map String to Integer
// less space, take some time to convert string to int
public class Codec {
    
    private Map<String, Integer> longToShortMap = new HashMap<>();
    private Map<Integer, String> shortToLongMap = new HashMap<>();
    private final String domain = "http://tinyurl.com/";
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(longUrl == null || longUrl.length() == 0) return "";
        int size = longToShortMap.size();
        if(!longToShortMap.containsKey(longUrl)) {
            longToShortMap.put(longUrl, size);
            shortToLongMap.put(size, longUrl);
        }
        return domain + convert10To62(longToShortMap.get(longUrl));
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if(shortUrl == null || shortUrl.length() == 0) return "";
        return shortToLongMap.get(convert62To10(shortUrl.substring(domain.length())));
    }

    private final String encodeLetter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String convert10To62(int num) {
        if(num == 0) return "0";
        char[] chs = encodeLetter.toCharArray();
        String res = "";
        while(num > 0) {
            res += chs[num % 62];
            num /= 62;
        }
        return res;
    }
    private int convert62To10(String s) {
        if(s == null || s.length() == 0) return -1;
        char[] letters = s.toCharArray();
        int num = 0;
        for(int i = 0; i < letters.length; i++) {
            int idx = encodeLetter.indexOf(letters[i]);
            num = num*62 + idx;
        }
        return num;
    }
}
// Solution #3: Hash
// easy to implement
// compute hash is time consuming. cannot avoid collision
public class Codec {

    Map<Integer, String> map = new HashMap<>();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        map.put(longUrl.hashCode(), longUrl);
        return "http://tinyurl.com/"+longUrl.hashCode();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}