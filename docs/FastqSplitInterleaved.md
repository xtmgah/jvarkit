# FastqSplitInterleaved

![Last commit](https://img.shields.io/github/last-commit/lindenb/jvarkit.png)

Split interleaved Fastq files.


## Usage

```
Usage: fastqsplitinterleaved [options] Files
  Options:
    -async, --async
      use async I/O
      Default: false
    -h, --help
      print help and exit
    --helpFormat
      What kind of help. One of [usage,markdown,xml].
    -md5, --md5
      write md5 file
      Default: false
    --version
      print version and exit
  * -a, -R1, -F
      R1 file
  * -b, -R2, -R
      R2 file

```


## Keywords

 * fastq


## Compilation

### Requirements / Dependencies

* java [compiler SDK 11](https://jdk.java.net/11/). Please check that this java is in the `${PATH}`. Setting JAVA_HOME is not enough : (e.g: https://github.com/lindenb/jvarkit/issues/23 )


### Download and Compile

```bash
$ git clone "https://github.com/lindenb/jvarkit.git"
$ cd jvarkit
$ ./gradlew fastqsplitinterleaved
```

The java jar file will be installed in the `dist` directory.

## Source code 

[https://github.com/lindenb/jvarkit/tree/master/src/main/java/com/github/lindenb/jvarkit/tools/fastq/FastqSplitInterleaved.java](https://github.com/lindenb/jvarkit/tree/master/src/main/java/com/github/lindenb/jvarkit/tools/fastq/FastqSplitInterleaved.java)


## Contribute

- Issue Tracker: [http://github.com/lindenb/jvarkit/issues](http://github.com/lindenb/jvarkit/issues)
- Source Code: [http://github.com/lindenb/jvarkit](http://github.com/lindenb/jvarkit)

## License

The project is licensed under the MIT license.

## Citing

Should you cite **fastqsplitinterleaved** ? [https://github.com/mr-c/shouldacite/blob/master/should-I-cite-this-software.md](https://github.com/mr-c/shouldacite/blob/master/should-I-cite-this-software.md)

The current reference is:

[http://dx.doi.org/10.6084/m9.figshare.1425030](http://dx.doi.org/10.6084/m9.figshare.1425030)

> Lindenbaum, Pierre (2015): JVarkit: java-based utilities for Bioinformatics. figshare.
> [http://dx.doi.org/10.6084/m9.figshare.1425030](http://dx.doi.org/10.6084/m9.figshare.1425030)


## Example

```bash
$ curl -sk "https://raw.githubusercontent.com/bigdatagenomics/adam/fff8ae259e8f6958eefd8de9a3ec39d33392fb21/adam-core/src/test/resources/interleaved_fastq_sample1.fq" |\
	java -jar dist/fastqsplitinterleaved.jar  -R1 R1.fastq.gz -R2 R1.fastq.gz 
```

