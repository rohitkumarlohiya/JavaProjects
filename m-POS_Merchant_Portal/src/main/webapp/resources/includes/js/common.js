function IsNumeric(sText)

{

    var ValidChars = "0123456789";

    var IsNumber = true;

    var Char;


    for (i = 0; i < sText.length && IsNumber;
         i++)

    {

        Char = sText.charAt(i);

        if (ValidChars.indexOf(Char) == -1)

        {

            IsNumber = false;

        }

    }

    return IsNumber;


}

//////////////////////// Fun to check a Alfanumeric value.

function AlfaNumeric(sText)

{

    var ValidChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    var IsNumber = true;

    var Char;

    for (i = 0; i < sText.length && IsNumber;
         i++)

    {

        Char = sText.charAt(i);

        if (ValidChars.indexOf(Char) == -1)

        {

            IsNumber = false;

        }

    }

    return IsNumber;

}
