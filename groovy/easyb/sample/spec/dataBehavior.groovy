
import sample.Data

before "Data の初期化", {
    data = new Data("test")
}

it "名前を持っている", {
    data.getName().shouldBe "test"
}

it "名前は変更できない", {
    ensureThrows(Exception) {
        data.setName("aaa")
    }
}