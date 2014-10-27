/**
 * Created by lianchen on 14-10-24.
 */
/*工厂模式*/
function createPerson(name, age, job){
    var o = new Object();
    o.name = name;
    o.age = age;
    o.job = job;
    o.sayName = function(){
        alert(this.name);
    };
    return o;
}

var person1 = createPerson("Nicholas",29,"IT");
var person2 = createPerson("Greg",27,"Doctor");


/*构造函数模式*/
function Person(name, age, job){
    this.name = name;
    this.age = age;
    this.job = job;
    this.sayName = function(){
        alert(this.name);
    }
}
var person3 = Person("Nicholas",29,"IT");
var person4 = Person("Greg",27,"Doctor");

/*原型模式*/
function Person(){

}
Person.prototype.name = "Nick";
Person.prototype.age = 29;
Person.prototype.job = "IT";
Person.prototype.sayName = function(){
    alert(this.name);
};
var person5 = new Person();
person5.sayName();

var person6 = new  Person();
person6.sayName();
alert(person5.sayName == person6.sayName);



