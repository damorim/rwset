#!/usr/bin/env sh

# got to project root
CURDIR="$(X=`pwd` && find $X -name "common" | sed "s/common//").."
cd $CURDIR

# create output folder if needed
OUTPUT_FOLDER="OUTPUT"
[ -d $OUTPUT_FOLDER ] || mkdir $OUTPUT_FOLDER

# generate svgs
echo "Generating graphics..."
dot -Tsvg ./src-tests/rwsets/bancosimples/TestBancoSimples.test0.dot > $OUTPUT_FOLDER/TestBancoSimples0.svg
dot -Tsvg ./src-tests/rwsets/bancosimples/TestBancoSimples.test1.dot > $OUTPUT_FOLDER/TestBancoSimples1.svg
dot -Tsvg ./src-tests/rwsets/bancosimples/TestBancoSimples.test3.dot > $OUTPUT_FOLDER/TestBancoSimples3.svg
dot -Tsvg ./src-tests/rwsets/bancosimples/TestBancoSimples.test4.dot > $OUTPUT_FOLDER/TestBancoSimples4.svg
dot -Tsvg ./src-tests/rwsets/chess/TestChess.test0.dot > $OUTPUT_FOLDER/TestChess0.svg
dot -Tsvg ./src-tests/rwsets/chess/TestChess.test1.dot > $OUTPUT_FOLDER/TestChess1.svg
dot -Tsvg ./src-tests/rwsets/chess/TestChess.test2.dot > $OUTPUT_FOLDER/TestChess2.svg
dot -Tsvg ./src-tests/rwsets/delivery/TestDelivery.test0.dot > $OUTPUT_FOLDER/TestDelivery0.svg
dot -Tsvg ./src-tests/rwsets/delivery/TestDelivery.test1.dot > $OUTPUT_FOLDER/TestDelivery1.svg
dot -Tsvg ./src-tests/rwsets/delivery/TestDelivery.test2.dot > $OUTPUT_FOLDER/TestDelivery2.svg
dot -Tsvg ./src-tests/rwsets/delivery/TestDelivery.test3.dot > $OUTPUT_FOLDER/TestDelivery3.svg
dot -Tsvg ./src-tests/rwsets/domino/TestDomino.test0.dot > $OUTPUT_FOLDER/TestDomino0.svg
dot -Tsvg ./src-tests/rwsets/domino/TestDomino.test1.dot > $OUTPUT_FOLDER/TestDomino1.svg
dot -Tsvg ./src-tests/rwsets/domino/TestDomino.test2.dot > $OUTPUT_FOLDER/TestDomino2.svg
dot -Tsvg ./src-tests/rwsets/domino/TestDomino.test3.dot > $OUTPUT_FOLDER/TestDomino3.svg

echo "Done :)"

# go back to previous folder
cd -
