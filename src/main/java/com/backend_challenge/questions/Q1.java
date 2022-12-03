package com.backend_challenge.questions;

public class Q1 {

    //1- MVC kavramını açıklar mısınız ? Neden ihtiyaç duyuluyor. Java’da nasıl kurgulanıyor.
    //Object Oriented katmanları nelerdir ?

    /*
     MVC Model-View-Controller isimli üç katmandan oluşur ve katmanları birbirinden bağımsız olarak çalışmaktadır.
     Yazdığımız kodların farklı amaçlara hizmet eden yapılarını birbirinden ayırarak, kodu daha rahat geliştirilebilir.
     Yani MVC nin birbirinden bağımsız oluşu sayesinde kullanıcı katmanında görsel bir değişiklik yapmak istenirse
     sadece görünümü değiştirecek kısımla yani View ile ilgilenilir. Kurgusu ise şu şekilde olur. Kullanıcıdan
     öncelikle bir request gelir. Bu Request'i karşılayan birim hangi Controller tarafından işleme girmesi gerektiğine
     karar verir. Route bilgisine göre Controller hangi Action’ı çalıştıracaksa o View çalıştırılır ve ViewResult
     döndürülür ve sonuç olarak HTTP tarafından Response döner.

     Model
     Veri tabanına erişim, veri tabanı ilişkileri gibi data ile ilgili işlemlerin yapıldığı katmandır.
     Yani data işlemleri bu katmanda gerçekleşir.

     View
     Bu katman kullanıcının ekranda gördüğü katman olarak adlandırılır. Bu kısımda arayüz teknolojileri kullanılır.

     Controller
     Kullanıcıların view üzerinden verdiği komutların Controller aracılığı ile model işlenmesini sağladığı katmandır,
     yani view ve model arasında kalan katmandır. Metodlar ve fonksiyonlar bu katmanda çağırılarak kullanılır.



     OOP
     Object oriented bir sınıftan new keyword’ü ile yeni bir obje üretip o sınıfa ait olan alanları ve methodları
     tekrar tekrar kullanmaya ve bir daha o kodu yazmaya gerek kalmamasına denir. Abstraction, Inheritance,
     Polimorfizm ve Encapsulation gibi önemli prensipleri vardır.

     Encapsulaton: sınıf içerisindeki verinin sınıf dışına  kontrollü şekilde bir şekilde açılmasıdır.
     Abstraction: Diğer adıyla soyutlama gereksiz ayrıntıları göz ardı ederek bir nesnenin yalnızca gerekli
     özelliklerini belirleme işlevi olarak da nitelendirilebilir.
     Inheritance: kalıtım ile bir sınıftan diğerine methodları ve alanları aktarmaktır.
     Polimorfizm: Nesne yönenimli programlama dillerinde çok biçimlilik aynı temel sınıftan türetilmiş
     olan yeni sınıflarda metotların farklı şekillerde davranabilmesidir.

     */



}
