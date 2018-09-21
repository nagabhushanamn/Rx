


function* func() {
    let i = 0;
    while (true) {
        i++;
        yield i;
    }
}

let it = func();
console.log(it.next());
console.log(it.next());
console.log(it.next());
console.log(it.next());
console.log(it.next());
console.log(it.next());
