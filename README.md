[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/JwSLLxUh)


<iframe
  src="https://carbon.now.sh/embed?bg=rgba%28171%2C+184%2C+195%2C+1%29&t=seti&wt=none&l=text%2Fx-java&width=680&ds=true&dsyoff=20px&dsblur=68px&wc=true&wa=true&pv=56px&ph=56px&ln=false&fl=1&fm=Hack&fs=14px&lh=133%25&si=false&es=2x&wm=false&code=public%2520static%2520void%2520migrate%28Connection%2520connection%29%2520throws%2520SQLException%2520%257B%250A%2520%2520%2520%2520%2520%2520%2520%2520try%2520%28Statement%2520statement%2520%253D%2520connection.createStatement%28%29%29%2520%257B%250A%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%252F%252F%2520Create%2520new%2520STUDENTS%2520table%2520with%2520updated%2520schema%250A%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520statement.executeUpdate%28%2522CREATE%2520TABLE%2520IF%2520NOT%2520EXISTS%2520STUDENTS_NEW%2520%28STUDENT_ID%2520SERIAL%2520PRIMARY%2520KEY%252C%2520ST_NAME%2520VARCHAR%2830%29%252C%2520ST_LAST%2520VARCHAR%2830%29%29%2522%29%253B%250A%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520System.out.println%28%2522Created%2520new%2520STUDENTS%2520table%2520with%2520updated%2520schema.%2522%29%253B"
  style="width: 1024px; height: 281px; border:0; transform: scale(1); overflow:hidden;"
  sandbox="allow-scripts allow-same-origin">
</iframe>
