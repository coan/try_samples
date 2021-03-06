
def cl = { arg ->
	if (arg > 10) {
		println "*** recurse"

		call(arg % 10)
		// 以下のようにすると MissingMethodException となる
		// cl(arg % 10)
	}
	else {
		println "result: ${arg}"
	}
}

cl(19)
cl(5)

println "-------------"

def cl2
cl2 = { arg ->
	if (arg > 10) {
		println "*** recurse"
		// 事前に空の変数を宣言しておけばよい
		cl2(arg % 10)
	}
	else {
		println "result: ${arg}"
	}
}

cl2(19)
cl2(5)

println "-------------"

def cl3
cl3 = { arg ->
	if (arg > 10) {
		println "*** recurse"
		cl3.trampoline(arg % 10)
	}
	else {
		println "result: ${arg}"
	}
}.trampoline()

cl3(19)
cl3(5)

