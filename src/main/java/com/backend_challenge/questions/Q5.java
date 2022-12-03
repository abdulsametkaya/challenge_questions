package com.backend_challenge.questions;

public class Q5 {

    //5- Firmada çalışman için sana remote bir linux server verildi. Elinde ip adresi port bilgisi
    //kullanıcı adi ve şifren var. Server a erişimi nasıl test edersin, Server a nasıl erişirsin, Server a
    //nasıl dosya atarsın, Serverdan nasıl dosya çekersin.

    /*
    Linux sunuculara erişim sağlamak için SSH protokolü kullanılıyor. SSH, iki bilgisayar arasında güvenli bağlantı
    kurmaya yarayan bir protokoldür. SSH istemcisi (client) Linux işletim sistemlerinde standart olarak geliyor.
    Herhangi bir bilgisayara bağlanmak istendiğinde ilgili komutu girilmesi gerekiyor : ssh -l kullanici_adi adres
    -p port_numarasi

    dosya transferi için:
    scp -P port_numarasi kaynak_dosya kullanici_adi@adres:hedef_dosya

     */



}
