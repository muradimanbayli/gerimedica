package nl.gerimedica.exception;

public class UnsupportedFormatException extends RuntimeException{
    public UnsupportedFormatException(String fileFormat) {
        super("System doesnot support the file format: "+fileFormat);
    }
}
