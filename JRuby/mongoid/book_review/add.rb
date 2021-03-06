require "rubygems"
require "mongoid"

require "models/book"
require "models/user"
require "models/comment"

Mongoid.configure do |config|
	config.master = Mongo::Connection.new.db("book_review")
	#キーの型を BSON::ObjectId にするための設定
	#
	#下記を有効にすると belongs_to を含む Model の検索で失敗するようになる
	#（belongs_to で保存される xxx_id の型が String のため）
	#config.use_object_ids = true
end

u1 = User.create(:name => 'fits')
u2 = User.create(:name => 'tester')

b1 = Book.new(:title => 'Rails')
b1.comments << Comment.new(:content => 'test1', :created_date => Time.now, :user => u1)
b1.comments << Comment.new(:content => 'test2', :created_date => Time.now, :user => u2)
b1.save

b2 = Book.new(:title => 'Hadoop')
b2.comments << Comment.new(:content => 'test1', :created_date => Time.now, :user => u1)
b2.save

Book.create(:title => 'nnode.js')

