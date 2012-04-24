#! /usr/bin/python3
# Program to convert karlo.txt into XML document, minus translations, key vocab, and grammar

import sys, re
from xml.dom.minidom import Document

input_file = open(sys.argv[1]).read()
output_file = open(sys.argv[2], "w")
content = re.split('([0-9]+)\. ', input_file)
content = content[1:]

doc = Document()
text = doc.createElement("annotatedtext")
doc.appendChild(text)

x = 0
while x < len(content) - 1:
    chapter = doc.createElement("chapter")
    chapter.setAttribute("num", content[x])
    x += 1
    title, remains = content[x].split("\n", 1)
    chapter.setAttribute("title", title)
    # create empty grammar node
    grammar = doc.createElement("grammar")
    grammar.appendChild(doc.createTextNode(""))
    chapter.appendChild(grammar)
    # create empty vocab list node
    vocab = doc.createElement("vocab")
    vocab.appendChild(doc.createTextNode(""))
    chapter.appendChild(vocab)
    # create paragraph nodes
    paragraphs = remains.split("\n\n")
    for para in paragraphs:
        para = para.strip()
        paragraph = doc.createElement("paragraph")
        words = re.split("\s", para)
        sentence = doc.createElement("sentence")
        for word in words:
            word_elem = doc.createElement("word")
            l1 = doc.createElement("l1")
            l1text = doc.createTextNode("")
            l1.appendChild(l1text)
            word_elem.appendChild(l1)
            l2 = doc.createElement("l2")
            l2text = doc.createTextNode(word)
            l2.appendChild(l2text)
            word_elem.appendChild(l2)
            sentence.appendChild(word_elem)
            if word.endswith(".") or word.endswith("!") or word.endswith("?"):
                trans = doc.createElement("trans")
                trans.appendChild(doc.createTextNode(""))
                sentence.appendChild(trans)
                paragraph.appendChild(sentence)
                sentence = doc.createElement("sentence")
        chapter.appendChild(paragraph)
    x += 1
    text.appendChild(chapter)

#print(doc.toprettyxml())
doc.writexml(output_file, indent="\t", addindent="\t", newl="\n")
