package de.michel.projecteuler.templates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author micmeyer
 */
public class PackageGenerator
{

    private static final File SRC_FOLDER = new File(System.getProperty("user.dir") + "/de/michel/projecteuler");

    private static final File TEMPLATE_FILE = new File(SRC_FOLDER.getAbsolutePath() + "/templates/ProblemTemplate.java");

    public static void main(String[] args)
    {
        createPackagesAndFiles(SRC_FOLDER, 101, 200, false);
    }

    private static void createPackagesAndFiles(File folder, int start, int end, boolean forceOverwrite)
    {
        for (int prob = start; prob < end; prob++)
        {
            String packageName = String.format("problem%04d", prob);
            File packageFolder = new File(folder.getAbsolutePath() + "/" + packageName);

            if (!packageFolder.exists() || forceOverwrite)
            {
                writeTemplate(packageFolder);
            }
        }
    }

    private static void writeTemplate(File folder)
    {
        String className = folder.getName().replace("p", "P");
        File file = new File(folder.getAbsolutePath() + "/" + className + ".java");

        createFileWithFolders(file);

        try (BufferedReader br = new BufferedReader(new FileReader(TEMPLATE_FILE));
                FileWriter fw = new FileWriter(file))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                fw.write(line.replace("ProblemTemplate", className).replace("templates", folder.getName()));
                fw.write("\n");
            }
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
        }
    }

    private static boolean createFileWithFolders(File file)
    {
        boolean success = true;
        try
        {
            if (!file.exists())
            {
                File parent = file.getParentFile();
                if (!parent.exists())
                {
                    success = parent.mkdirs();
                }
                if (success)
                {
                    success = file.createNewFile();
                }
            }
            else
            {
                success = false;
            }
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
        }

        return success;
    }
}
