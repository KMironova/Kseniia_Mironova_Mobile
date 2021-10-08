# Kseniia_Mironova_Mobile
repository for mobile homework

For start tests from this branch you should:
- in class \src\main\java\setup\BaseTest paste your API_KEY, which you can get from this resource https://mobilecloud.epam.com/#!/settings
- in suites androidNativeTNG.xml, androidWebTNG.xml, appleWebTNG.xml, appleNativeTNG.xml change parameter value for devies you want to test. You can find them here: https://mobilecloud.epam.com/#!/devices. For android use manufacturer name + model. For iOS use seril number.
- run test with command:
                  mvn clean test -P androidNative -> for runnig android native test
                  mvn clean test -P androidWeb -> for running android web test
                  mvn clean test -P appleWeb -> for running apple web test
                  mvn clean test -P appleNative -> for running apple native test
                  

