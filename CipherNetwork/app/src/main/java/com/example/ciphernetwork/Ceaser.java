package com.example.ciphernetwork;

public class Ceaser
{
    public String encryption(String s)
    {
        String s1= "abcdefghijklmnopqrstuvwxyz" ;
        String encrypted = "";
        char arr[]=new char[26];
        for(int i=0;i<26;i++)
        {
            char c=s1.charAt(i);
            arr[i]=c;
        }
        ///
        String s11=s.toLowerCase();
        int len=s11.length();
        for(int i=0;i<len;i++)
        {
            char c=s11.charAt(i);
            if(c==' ')
                encrypted = encrypted + " ";
            else
            {
                for(int j=0;j<26;j++)
                {
                    if(c==arr[j])
                    {
                        if(c=='z')
                            encrypted = encrypted + "a" ;
                        else
                            encrypted = encrypted + arr[j+1];
                    }
                }
            }
        }
        return(encrypted);
    }

    public String decryption(String s)
    {
        String s1= "abcdefghijklmnopqrstuvwxyz" ;
        String decrypted = "";
        char arr[]=new char[26];
        for(int i=0;i<26;i++)
        {
            char c=s1.charAt(i);
            arr[i]=c;
        }

        String code = s;
        String code1=code.toLowerCase();
        int lencd=code1.length();
        for(int i=0;i<lencd;i++)
        {
            char c1=code1.charAt(i);
            if(c1==' ')
                decrypted = decrypted + " ";
            else
            {
                for(int j=0;j<26;j++)
                {
                    if(c1==arr[j])
                    {
                        if(c1=='a')
                            decrypted = decrypted + "z";
                        else
                            decrypted = decrypted + arr[j-1];
                    }

                }
            }
        }
        return(decrypted);
    }


}//class
