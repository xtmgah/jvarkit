# VcfCreateDictionary

![Last commit](https://img.shields.io/github/last-commit/lindenb/jvarkit.png)

Create a SAM Sequence Dictionary from a set of VCF files.


## Usage

```
Usage: vcfmakedict [options] Files
  Options:
    -h, --help
      print help and exit
    --helpFormat
      What kind of help. One of [usage,markdown,xml].
    -o, --output
      Output file. Optional . Default: stdout
    --version
      print version and exit

```


## Keywords

 * vcf
 * dict
 * fai


## Compilation

### Requirements / Dependencies

* java [compiler SDK 11](https://jdk.java.net/11/). Please check that this java is in the `${PATH}`. Setting JAVA_HOME is not enough : (e.g: https://github.com/lindenb/jvarkit/issues/23 )


### Download and Compile

```bash
$ git clone "https://github.com/lindenb/jvarkit.git"
$ cd jvarkit
$ ./gradlew vcfmakedict
```

The java jar file will be installed in the `dist` directory.

## Source code 

[https://github.com/lindenb/jvarkit/tree/master/src/main/java/com/github/lindenb/jvarkit/tools/misc/VcfCreateDictionary.java](https://github.com/lindenb/jvarkit/tree/master/src/main/java/com/github/lindenb/jvarkit/tools/misc/VcfCreateDictionary.java)


## Contribute

- Issue Tracker: [http://github.com/lindenb/jvarkit/issues](http://github.com/lindenb/jvarkit/issues)
- Source Code: [http://github.com/lindenb/jvarkit](http://github.com/lindenb/jvarkit)

## License

The project is licensed under the MIT license.

## Citing

Should you cite **vcfmakedict** ? [https://github.com/mr-c/shouldacite/blob/master/should-I-cite-this-software.md](https://github.com/mr-c/shouldacite/blob/master/should-I-cite-this-software.md)

The current reference is:

[http://dx.doi.org/10.6084/m9.figshare.1425030](http://dx.doi.org/10.6084/m9.figshare.1425030)

> Lindenbaum, Pierre (2015): JVarkit: java-based utilities for Bioinformatics. figshare.
> [http://dx.doi.org/10.6084/m9.figshare.1425030](http://dx.doi.org/10.6084/m9.figshare.1425030)



## Example

```
$ java -jar dist/vcfmakedict.jar test.vcf  mutations.vcf

@HD	VN:1.5
@SQ	SN:3	LN:124490595
@SQ	SN:19	LN:57230811
@SQ	SN:rotavirus	LN:1064
```


