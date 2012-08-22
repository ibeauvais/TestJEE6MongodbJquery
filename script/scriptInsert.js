
db.User.remove();
var users=[
{"className":"fr.simplechat.model.User","email":"email1@domaine.fr","password":"123456","name":"user1"}
,{"className":"fr.simplechat.model.User","email":"email2@domaine.fr","password":"7891011","name":"user2"}
,{"className":"fr.simplechat.model.User","email":"email3@domaine.fr","password":"12131415","name":"user3"}];
users.forEach(function (user){
db.User.save(user);
});