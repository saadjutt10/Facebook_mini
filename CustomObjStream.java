import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class CustomObjStream extends ObjectOutputStream{

    protected CustomObjStream() throws IOException, SecurityException {
        super();
        //TODO Auto-generated constructor stub
    }
    
    public CustomObjStream(OutputStream out) throws IOException {
        super(out);
    }

    public void writeStreamHeader(){

    }
}
