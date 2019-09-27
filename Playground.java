    String[] text = new String[4];
    text[0] = "ID1,Minneapolis,shoes,2,Air";
    text[1] = "ID2,Chicago,shoes,1,Air";
    text[2] = "ID3,Central Department Store,shoes,5,BonPied";
    text[3] = "ID4,Quail Hollow,forks,3,Pfitzcraft";
    List<String> list = Arrays.asList(text);
    Map<String, Integer> brand =
        list.stream()
            .collect(
                Collectors.groupingBy(
                    word -> word.split(",")[2],
                    Collectors.summingInt(word -> Integer.parseInt(word.split(",")[3]))));
    Map<String,List<String>> hottest = list.stream()
        .collect(
            Collectors.groupingBy(
                word -> word.split(",")[2],
                Collectors.mapping(
                    word -> word.split(",")[4],
                    Collectors.toList())));

    Map<String,String> res = hottest.entrySet().stream()
        .collect(Collectors.toMap(
            key -> key.getKey(),
            value -> value.getValue().stream().
        ));