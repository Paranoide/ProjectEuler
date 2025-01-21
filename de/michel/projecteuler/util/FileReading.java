package de.michel.projecteuler.util;

import java.io.File;
import java.net.URL;

public class FileReading
{
    public static File getFile(Class<?> classInPackageOfFile, String fileName)
    {
        URL resource = classInPackageOfFile.getResource(fileName);

        if (resource == null)
            throw new IllegalArgumentException(fileName + " could not be found.");

        return new File(resource.getFile());
    }
}
