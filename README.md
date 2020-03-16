# Highlighter

Repository contains a small project which helps to find keywords in text and highlight those.
We highlight found keywords by surroinding those with <b></b>.
Please note that the bigger portions of matches have higher priority, so that if there is a part of the string matched with a keyword, but there is a bigger keyword match that overlaps it, we prefer the bigger keyword match.	

## Dependencies

* [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
* [Maven](https://maven.apache.org/)

## Download/Build/Run
Get the highlighter with:
```
git clone https://github.com/hbeybutyan/highlighter.git
```

Build a jar with:
```
cd highlighter
mvn package 
```

Run highlighter with:
```
java -jar ./target/highlighter-1.0-SNAPSHOT.jar "input_text" "keyword_1" ["keyword_2" ... ]
```
