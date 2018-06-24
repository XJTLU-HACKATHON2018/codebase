# 西交利物浦2018年黑客马拉松


**请每个队伍，建立自己的项目分支，把代码放在自己分支内，具体操作流程如下**

* step 1 安装git在本地

[Git Downloads](https://git-scm.com/downloads)  


* step 2 初始化git设置

在命令行下输入自己的名字和邮箱
```
$git config --global user.name "John Doe"

$git config --global user.email johndoe@example.com

$git config --list #检查git的设置
```

* step 3 clone代码库到本地
```
$git clone https://github.com/XJTLU-HACKATHON2018/codebase.git
```

* step 4 在本地创建自己的版本分支，并切换到自己的分支
```
$cd codebase

$git branch your_proejct_name #输入你的项目名称

$git checkout your_proejct_name #输入你的项目名称

$git status #查看当前状态，自己所处的分支
```

* step 5 拷贝自己的代码到git所在的目录
```
把你的工程代码拷贝的当前文件夹
```

* step 6 提交代码到本地的git

```
$git status #应该可以看到一堆你刚拷贝过来的文件名，大致样子如下
Untracked files:
(use "git add <file>..." to include in what will be committed)
xxxxx

$git add . #跟踪本地文件

$git status #再次运行, 应该看到如下的一些提示
Changes to be committed:
(use "git reset HEAD <file>..." to unstage)
xxxxx

$git commit -m 'initial push' . #提交本地文件，initial push是提交说明，可以自定义

```

* step 7 映射本地分支到远程分支
```
$ git push --set-upstream origin your_branch_name #输入你的分支名
```
*此时提示你输入用户名:
XJTLU-HACKATHON2018(大写)
再输入密码：
见群内信息*
