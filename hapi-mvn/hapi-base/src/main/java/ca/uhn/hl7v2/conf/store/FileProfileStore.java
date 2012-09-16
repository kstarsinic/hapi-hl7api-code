package ca.uhn.hl7v2.conf.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stores profiles in a local directory.  Profiles are stored as text
 * in files named ID.xml (where ID is the profile ID).
 * @author Bryan Tripp
 */
public class FileProfileStore implements ProfileStore {
    
    private File root;
    private static final Logger log = LoggerFactory.getLogger(FileProfileStore.class);
    
    /** Creates a new instance of FileProfileStore */
    public FileProfileStore(String file) {
        root = new File(file);
        if (!root.isDirectory())
            if (!root.mkdirs()) 
                throw new IllegalArgumentException(file + " is not a directory");
    }
    
    /**
     * Retrieves profile from persistent storage (by ID).  Returns null
     * if the profile isn't found.
     */
    public String getProfile(String ID) throws IOException {
        String profile = null;
        
        File source = new File(getFileName(ID));
        if (source.isFile()) {
            BufferedReader in = new BufferedReader(new FileReader(source));
            char[] buf = new char[(int) source.length()];
            int check = in.read(buf, 0, buf.length);
            in.close();
            if (check != buf.length)
                throw new IOException("Only read " + check + " of " + buf.length
                + " bytes of file " + source.getAbsolutePath());
            profile = new String(buf);
        }
        log.debug("Got profile {}: \r\n {}", ID, profile);
        return profile;
    }
    
    /**
     * Stores profile in persistent storage with given ID.
     */
    public void persistProfile(String ID, String profile) throws IOException {
        File dest = new File(getFileName(ID));
        BufferedWriter out = new BufferedWriter(new FileWriter(dest));
        out.write(profile);
        out.flush();
        out.close();
    }
    
    private String getFileName(String ID) {
        return root.getAbsolutePath() + "/" + ID + ".xml";
    }
}
