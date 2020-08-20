# VcfToHilbert

![Last commit](https://img.shields.io/github/last-commit/lindenb/jvarkit.png)

Plot a Hilbert Curve from a VCF file.


## Usage

```
Usage: vcf2hilbert [options] Files
  Options:
    -h, --help
      print help and exit
    --helpFormat
      What kind of help. One of [usage,markdown,xml].
    -o, --out
      Output file. Optional . Default: stdout
    -r, --radius
      Radius Size
      Default: 3.0
    --version
      print version and exit
    -w, --width
      Image width
      Default: 1000

```


## Keywords

 * vcf
 * image
 * visualization


## Compilation

### Requirements / Dependencies

* java [compiler SDK 11](https://jdk.java.net/11/). Please check that this java is in the `${PATH}`. Setting JAVA_HOME is not enough : (e.g: https://github.com/lindenb/jvarkit/issues/23 )


### Download and Compile

```bash
$ git clone "https://github.com/lindenb/jvarkit.git"
$ cd jvarkit
$ ./gradlew vcf2hilbert
```

The java jar file will be installed in the `dist` directory.

## Source code 

[https://github.com/lindenb/jvarkit/tree/master/src/main/java/com/github/lindenb/jvarkit/tools/hilbert/VcfToHilbert.java](https://github.com/lindenb/jvarkit/tree/master/src/main/java/com/github/lindenb/jvarkit/tools/hilbert/VcfToHilbert.java)


## Contribute

- Issue Tracker: [http://github.com/lindenb/jvarkit/issues](http://github.com/lindenb/jvarkit/issues)
- Source Code: [http://github.com/lindenb/jvarkit](http://github.com/lindenb/jvarkit)

## License

The project is licensed under the MIT license.

## Citing

Should you cite **vcf2hilbert** ? [https://github.com/mr-c/shouldacite/blob/master/should-I-cite-this-software.md](https://github.com/mr-c/shouldacite/blob/master/should-I-cite-this-software.md)

The current reference is:

[http://dx.doi.org/10.6084/m9.figshare.1425030](http://dx.doi.org/10.6084/m9.figshare.1425030)

> Lindenbaum, Pierre (2015): JVarkit: java-based utilities for Bioinformatics. figshare.
> [http://dx.doi.org/10.6084/m9.figshare.1425030](http://dx.doi.org/10.6084/m9.figshare.1425030)


##Example

```bash
$  curl -s "http://ftp.1000genomes.ebi.ac.uk/vol1/ftp/technical/working/20140123_NA12878_Illumina_Platinum/NA12878.wgs.illumina_platinum.20140404.snps_v2.vcf.gz" | gunzip -c |\
 java -jar dist/vcf2hilbert.jar  -r 1.1 -w 1000 -o hilbert.png
```

