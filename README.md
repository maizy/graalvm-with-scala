# Test GraalVM with simple Scala Apps

## Tutorial

1. Setup GraalVM - https://www.graalvm.org/docs/getting-started/

Tested on `1.0.0-rc1` EE.

2. Enable GraalVM PATH

```
export PATH=/path/to/graalvm-1.0.0-rc1/Contents/Home/bin:$PATH
```

3. Assembly sample apps

```
cd scala-helloworld
sbt assembly
cd ..
```

```
cd scala-cli
sbt assembly
cd ..
```


4. Run on JVM

scala-helloworld:

```
cd scala-helloworld
time java -jar target/scala-2.12/scalahw-assembly-0.1.0-SNAPSHOT.jar A B C D
cd ..
```
=>

```
Hello World: A, B, C, D
1.87s user 0.20s system 204% cpu 1.018 total
```

scala-cli:

```
cd scala-cli
time java -jar target/scala-2.12/scalacli-assembly-0.1.0-SNAPSHOT.jar gen -o /tmp
cd ..
```
=>
```
Opts: AppOptions(/tmp,Some(gen))
2.13s user 0.23s system 215% cpu 1.094 total
```

5. Build native images

```
cd scala-helloworld
native-image -jar target/scala-2.12/scalahw-assembly-0.1.0-SNAPSHOT.jar
cd ..
```

```
cd scala-cli
native-image -jar target/scala-2.12/scalacli-assembly-0.1.0-SNAPSHOT.jar
cd ..
```

6. Run binaries

scala-helloworld:


```
time scala-helloworld/scalahw-assembly-0.1.0-SNAPSHOT A B C D
```

=>

```
Hello World: A, B, C, D
0.01s user 0.01s system 42% cpu 0.045 total
```

scala-cli:

```
time scala-cli/scalacli-assembly-0.1.0-SNAPSHOT gen -o /tmp
```

=>

```
Opts: AppOptions(/tmp,Some(gen))
0.01s user 0.02s system 45% cpu 0.053 total
```

## Found uncompatible scala features

0. See general Java limitations [here](https://github.com/oracle/graal/blob/master/substratevm/LIMITATIONS.md).

1. DelayInit trait

```
object MyApp extends App {
    //...
}
```

because of generated delayedInit method inside scala.App.

Workaround: use simple Object with `main(args Array[String])` method.


## TODO

Test more complicated apps:

* [ ] cli tool
* [ ] scala features: pattern matching, implicits, HKT
* [ ] scala/python interopt + native image
* [ ] scala/js interopt + native image
* ...
