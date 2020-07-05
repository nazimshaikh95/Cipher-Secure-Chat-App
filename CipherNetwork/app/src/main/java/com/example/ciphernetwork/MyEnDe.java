package com.example.ciphernetwork;

public class MyEnDe
{
    public String encryption(String ss)
    {
        String s1= "abcdefghijklmnopqrstuvwxyz" ;
        String encrypted="";
        char arr[]=new char[26];
        for(int i=0;i<26;i++)
        {
            char c=s1.charAt(i);
            arr[i]=c;
        }

        String s=ss.toLowerCase();
        int len=s.length();
        for(int i=0;i<len;i++)
        {
            char c = s.charAt(i);
            if(c==' ')
                encrypted = encrypted + "27";
            else
            {
                for(int j=0;j<26;j++)
                {
                    if(c==arr[j])
                    {
                        if(j<9)
                            encrypted = encrypted + "0" +(j+1);
                        else
                            encrypted = encrypted + (j+1);
                    }
                }
            }
        }
        return(encrypted);
    }

    public String decryption(String ss)
    {
            String s1 = "abcdefghijklmnopqrstuvwxyz";
            String decrypted = "";
            char arr[] = new char[26];
            for (int i = 0; i < 26; i++) {
                char c = s1.charAt(i);
                arr[i] = c;
            }
            String code = ss;
            try {
                int lencd = code.length();

                for (int i = 0; i < lencd; i++) {
                    char c1 = code.charAt(i);
                    char c2 = code.charAt(i + 1);
                    i++;
                    String s = String.valueOf(c1) + String.valueOf(c2);
                    int cd = Integer.parseInt(s);
                    if (cd == 27)
                        decrypted = decrypted + " ";
                    else
                        decrypted = decrypted + (arr[cd - 1]);
                }
                return(decrypted);
            }catch(Exception e){System.out.println(e);
            return("100");
            }

    }
}
