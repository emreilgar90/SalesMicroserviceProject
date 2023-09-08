# GENEL İÇERİK VE NOTLAR 

## Docker Kullanımları;
    docker run --name postgreDocker -e POSTGRES_PASSWORD=root -p 9999:5432 -d postgres
   
## Microservice ya da projenin jar olarak export edilmesi
    1-Öncelikle ilgili microservice in gradle dosyası üzerinde build gradle yapmanız gereklidir.
    2-Sonra aynı konumda iken buildDependents yapmalısınız.
         Detaylar;
       * Bu işlemlerden sonra ilgili microservice altında build adında bir klasör gelecektir.
         bu klasörün altında bulunan libs klasörünün altında proje isimlendirmeniz ve versiyonun 
         olduğu bir jar dosyası bulacaksınız. Bu jar dosyası çalışabilir bir dosyadır.

## Docker Image Oluşturma
     Öncelikle, projenizin üzerinde çalışacağı mevcut bir container'a ihtiyaç var. Bu nedenle
     bizim sistemimize uygun olan bir dockerImage seçmeniz gerekiyor. 
     * Bize uygun olan image JavaJDK17 olacaktır. (amazoncorretto:17)
     * Kendi jar dosyanızı bu image üzerine kopyalıyorsunuz.
     * OPSİYONEL !! her image genellikle eski paketleri içerir. Bu nedenle update edilmesi tavsiye edilir.
     * jar dosyanızı çalıştırırsanız  (belli sistem enviromentlarını belirlemeniz gerekebilir.)
    
     Not: Dockerfile oluşturmanızın ve kullanmanızın iki yolu var;
      1-Dockerfile microservice içine oluşturulur ve çalıştırılır.(Güncellemek ve düzenlemek zor olabilir
       kodlama yaparken ilgili klasörün içinde olduğunuzdan emin olmanız gerekiyor.)
      
      2-Dockerfile projenin root klasörüne oluşturulur ve çalıştırılır.(Burada ise, tek dosya üzerinden
        çalıştığı için güncelleme yapmak kolay olur, ancak her microservice için ayrıca klasör konumunu
        belirtmek gerekir.)
## Docker Image Oluşturma (Dockerfile)
     1-   docker build -t <image_name> . : dockerfile   üzerinden gerekli yapılandırmaya bakarak image
          oluşturur. Ancak bizim yapımızda ARG olduğu için bu şekilde çalışmaz.
     2-   docker build --build-arg JAR_FILE=<jar_file_name> -t <image_name> . :   bu şekilde çalışır

### auth-microservice-server build işlemi;

    docker build --build-arg JAR_FILE=auth-microservice/build/libs/auth-microservice-v.0.0.1.jar -t java4/authservice:v001 .
    docker run -p 9090:9090 -d java4/authservice:v001 
    docker run -p 9090:9090 -d -e AUTH_PORT=9090 java4/authservice:v001       //sistem değişkenlerinden bilgi alıyorsa 
                                                                              //-e AUTH_PORT=9090
### config-server build işlemi;

    docker build --build-arg JAR_FILE=config-server/build/libs/config-server-v.0.0.1.jar -t java4/configserver:v001 .
    docker run -p 8888:8888 -d java4/configserver:v001 

### karşılaşılan hatalar;
    
     -Microservice içinde eğer enviroment variable kullanıyorsanız, docker run yaparken mutlaka bu parametreleri
      girmeniz gereklidir. ÖRN: -e AUTH_PORT=9090 şeklinde port ortam değişkeni olarak eklenmeli. 

    -- localhost olarak bırakılan tüm konumlar, container içinde aranacağı için hataya neden olmaktadır. 
    Burada yapmanız gereken şey, ulaşmak istediğiniz ip adresini tam olarak yazmaktır.
    ÖRN: localhost:5432 yerine 192.168.1.25:5432 şeklinde yazmanız gerekiyor.
    localhost:8888 yerine 192.168.1.25:8888 şeklinde yazmanız gerekiyor.

    - PostgreSQL in güvenlik önlemi olarak locahost dışında tüm erişimlere kendisini kapatmasıdır. bu nedenle,
    localhost yerinde ip adresi kullandığınızda erişimi reddetmektedir. bu nedenle,
    C:\Program Files\PostgreSQL\14\data\pg_hba.conf dosyasında düzenleme yapmanız gerekiyor. aşağıdaki şekilde
    gerekli dzületmeyi yapınız.
    # TYPE  DATABASE        USER            ADDRESS                 METHOD

    # "local" is for Unix domain socket connections only
    host  	all  		all 		0.0.0.0/0 		md5
    # IPv4 local connections:
    host    all             all             127.0.0.1/32            scram-sha-256
    # IPv6 local connections:
    host    all             all             ::1/128                 scram-sha-256
    # Allow replication connections from localhost, by a user with the
    # replication privilege.
    local   replication     all             			scram-sha-256
    host    replication     all             127.0.0.1/32            scram-sha-256
    host    replication     all             ::1/128                 scram-sha-256

# MongoDB Docker kullanınımı hakkında

### Docker üzerinde çalıştırmak
    docker run --name localdockermongo -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root -p 27000:27017 -d mongo:4.2.23

### Docker üzerinde ki mongoDb'ye bağlanmak

    MongoDb Compass(masaüstü uygulaması) kullanarak bağlanabilirsiniz. 
    Yeni bağlantı yaparak,
    localhost:27000 adresini kullanarak bağlanabilirsiniz.
    auth kısmında -> username: root, password: root şeklinde bağlanabilirsiniz.
    databasename boş bırakılacak.
    DİKKAT!!!
    MongoDB de bir veritabanına erişim Root kullanıcı ile yapılmaz. default ta
    kullanıcı yeni eklenen DB ye yönetici olarak eklenmez. ve root şifresi ile erişim
    doğru değildir. bu nedenle her DB için yetkili bir kullanıcı oluşturmak gereklidir.
    Kullanıcı oluşturma işlemi için Mongo Compass kullanılabilir.
 
      MONGO COMPASS'A GİT SOL ALT KÖŞEDE MONGOSH yazan yere hangi db hakkında yetkilendirme yapmak
      istiyorsan o db   "use Java4Db" gibi seç
    1- use [DB ADI] - Java4Db
    2- db.createUser(
        {
           user: "JavaUser",
           pwd: "Aa123456",
           roles: [ "readWrite", "dbAdmin"]
        }
    )
    NOT: Böyle yapıştırın: db.createUser({user: "JavaUser",pwd: "Aa123456",roles: [ "readWrite", "dbAdmin"]})
    3- bu işlemlerden sonra mutlaka yen oluşturduğunuz kullanıcı ile bağlatıyı deneyin.

### ZİPKİN Server docker üzerinde çalıştırmak ve ayarlamalar
     docker run -d -p 9411:9411 openzipkin/zipkin

    1- Gerekli bağımlılıklar genel build.gradle a eklenir.
    2- application.yml içinde zipkin ayarları yapılır.
    zipkin:
      enabled: true
      base-url: http://localhost:9411
      service:
        name: config-server

###  REDIS server kullanımı
     
    docker run --name localredis -d -p 6379:6379 redis
    1- Öncelikle bağımlılıkları ekliyoruz. DİKKAT tüm sisteme değil kullanacak servisltere ekleyin.
    2- redis için bir config dosyayı yapılandırmalsısınız.
    3-
    @Configuration
    @EnableCaching // redis üzerinde kullanmak üzere spring cache in aktif edilmesi içni gereklidir.
    @EnableRedisRepositories
    public class RedisConfiguration {    
    @Bean
    public LettuceConnectionFactory redisLettuceConnectionFactory(){
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost",6379));
      }    
    }

###  ElasticSearch Kurulumu ve Kullanımı
    1- Gerekli bağımlılıklar tanımlanır ve eklenir.
    2- ElasticSearch için sonraki adım spring.io dökümantasyonu okunmalıdır. çünkü hangi spring boot versiyonunun
    hangi elasticSearch versiyonu ile uyumlu olduğu bilinmelidir.
    3- Elasticsearch kullanımı için ayrı bir modül oluşturmak çok daha mantıklıdır.
    4- ilgili elasticsearch docker imajı kurulur.
    *  docker network create somenetwork
    *  docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms1024m -Xmx2048m" elasticsearch:7.17.7
    *! elasticsearch'e ram kısıtlaması getirmek için ; -e ES_JAVA_OPTS="-Xms1024m -Xmx2048m" bunu yazdık
    * yazmasan sistemin tüm ram'ini kullanmaya çalışacaktır !
    5- ElasticSearch için bir application.yml dosyasına ilgili parametereler yazılır.
    spring:
       elasticsearch:
    uris: http://localhost:9200
    6- Eğer elasticsearch için data analiz yapcaksanız, verilerinizi kontrol edecek iseniz
    kibana kurmanız tavsiye edilir.
    * docker run -d --name kibana --net somenetwork -p 5601:5601 kibana:7.17.7

###  GraphQL Kullanımı
    graphqls dosyası içinde tanımlanan data type'larda ! işareti zorunlu tanımlı
    alanları belirtir (null olamaz). ! işareti olmayan alanlar null olabilir.

### Docker Image larının Upload Edilmesi
    Docker Desktop üzerinde oluşturulan imajlarınızı, Docker Hub a push etmek için öncelikle hub hesabınızı
    web üzerinden açınız, sonra dcker desktop üzerinden login olmayı denediğinizde otomatik sizi yönlendirecek
    ve hesabınızı açacaktır. Ardından docker.hub a göndermek istediğiniz image ın  sağtarafında bulunan
    3 noktaya basarak "push" butonuna basmanız yeterlidir. Bu işlem image boyutuna göre uzun sürebilir.
    sonraki yüklemeleriniz, daha kısa sürecektir.

# RabbitMQ Docker kurulumu

    docker run -d --name some-rabbit -e RABBITMQ_DEFAULT_USER=java4 -e RABBITMQ_DEFAULT_PASS=root -p 5672:5672 -p 15672:15672 rabbitmq:3-management
    1- RabbitMQ arayüzüne erişim için http://localhost:15672/ adresine gidilir.
    2- Java içinden api desteği ve erişim için post olarak 5672 kullanılır.


