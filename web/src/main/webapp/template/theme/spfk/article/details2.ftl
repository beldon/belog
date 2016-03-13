<#include "../common/_layout.ftl"/>
<@html title="">
<article id="post-hello-world" class="article article-type-post" itemscope itemprop="blogPost">

    <div class="article-meta">
        <a href="/2016/01/16/hello-world/" class="article-date">
            <time datetime="2016-01-16T05:58:41.000Z" itemprop="datePublished">2016-01-16</time>
        </a>
    </div>

    <div class="article-inner">

        <input type="hidden" class="isFancy"/>


        <header class="article-header">

            <h1 class="article-title" itemprop="name">
                前端知识体系
            </h1>

        </header>

        <div class="article-info article-info-post">

            <div class="article-category tagcloud">
                <a class="article-category-link" href="/categories/HTML-书籍/">HTML 书籍</a>
            </div>


            <div class="article-tag tagcloud">
                <ul class="article-tag-list">
                    <li class="article-tag-list-item"><a class="article-tag-list-link" href="/tags/HTML-标签/">HTML 标签</a>
                    </li>
                    <li class="article-tag-list-item"><a class="article-tag-list-link" href="/tags/hello/">hello</a>
                    </li>
                </ul>
            </div>

            <div class="clearfix"></div>
        </div>




        <div class="article-entry" itemprop="articleBody">


            <p>来到GitHub这么长时间，才开始真真的了解GitHub，这个国外的代码托管平台，充满着大牛的身影。<br>国内也有不多少的代码托管平台，本文将就用GitHub的GitHub Pages 功能来搭建，我的个性博客，最近在学习JS后端Node.js, 现在火的不行, 异步IO的机制, 所以在学习过程中发现了<a href="2">hexo</a>是由Node.js驱动的一款快速、简单且功能强大的博客框架。<br>比起WordPress，hexo的搭建更加简洁，配合上markdown的使用，更加便捷的管理自己的学习文档。<br><a id="more"></a></p>
            <h1 id="u6982_u51B5"><a href="#u6982_u51B5" class="headerlink" title="概况"></a>概况</h1><blockquote>
            <ol>
                <li>为什么选择<a href="1">GitHub Pages</a><br>1、<a href="1">GitHub Pages</a>有免费的代码托管空间，资料自己管理，保存可靠；<br>2、学着用 GitHub，享受 GitHub 的便利，上面有很多大牛，眼界会开阔很多；<br>3、顺便理解 GitHub 工作原理，最好的团队协作流程；<br>4、GitHub建立私有仓库才会收费，所以会有很多开源代码。</li>
                <li><a href="1">GitHub Pages</a>是什么<br>应用GitHub Pages创建属于自己的个人博客，GitHub将提供免费的空间。GitHub提供的域名（用户名+github+io）,在Repository name对应处填写资源名，其需要使用自己的用户名，每个用户名下面只能建立一个，并且资源命名必须符合这样的规则username/username.github.io，之后勾选下面的”Initialize this repository with a README” 。</li>
                <li><a href="2">hexo</a>出自何人<br>hexo出自台湾大学生 tommy351 之手，是一个基于Node.js的静态博客程序，其编译上百篇文字只需要几秒。hexo生成的静态网页可以直接放到GitHub Pages，BAE，SAE等平台上。</li>
            </ol>
        </blockquote>
            <h1 id="u5B89_u88C5_u51C6_u5907"><a href="#u5B89_u88C5_u51C6_u5907" class="headerlink" title="安装准备"></a>安装准备</h1><blockquote>
            <p>环境搭建：</p>
            <ol>
                <li><a href="http://nodejs.org/" target="_blank" rel="external">Node.js</a>：下载<a href="https://nodejs.org/en/" target="_blank" rel="external">地址</a></li>
                <li><a href="http://git-scm.com/" target="_blank" rel="external">Git</a>：下载<a href="https://git-scm.com/download/win" target="_blank" rel="external">地址</a></li>
                <li><a href="http://www.sublimetext.com/" target="_blank" rel="external">Sublime</a>：下载<a href="http://www.sublimetext.com/2" target="_blank" rel="external">地址</a><h2 id="u5B89_u88C5Node"><a href="#u5B89_u88C5Node" class="headerlink" title="安装Node"></a>安装Node</h2>到 Node.js 官网下载相应平台的 最新版本 ，一路安装即可。我用的是 node-v0.10.22-x86.msi<h2 id="u5B89_u88C5Git"><a href="#u5B89_u88C5Git" class="headerlink" title="安装Git"></a>安装Git</h2>Git的客户端很多，我用的是 msysgit ，喜欢用绿色版本 Portable application for official Git for Windows 1.8.4 ，下载下来设置一下环境变量即可，Git_HOME，%Git_HOME%\bin之类的，不多说。<h2 id="u5B89_u88C5Sublime"><a href="#u5B89_u88C5Sublime" class="headerlink" title="安装Sublime"></a>安装Sublime</h2>Sublime Text 2 在这里仅仅作为一个文本编辑器使用，支持各种编程语言和文件格式，当然也支持Markdown语法，实在是个不可多得的练码奇才。喜欢追鲜的也可以尝试处于beta版本的 Sublime Text 3 。</li>
            </ol>
        </blockquote>
            <h1 id="GitHub_u6CE8_u518C_u4E0E_u914D_u7F6E"><a href="#GitHub_u6CE8_u518C_u4E0E_u914D_u7F6E" class="headerlink" title="GitHub注册与配置"></a>GitHub注册与配置</h1><blockquote>
            <ol>
                <li><p>注册：<br>访问：<a href="0">GitHub</a>，注册你的username和邮箱，邮箱十分重要，GitHub上很多通知都是通过邮箱的。注册过程比较简单，详细也可以看：<br>使用Github Page搭建博客, 需要遵循一定的规则, 需要在github建立仓库,仓库名为Github用户.github.io, 更多详情请参考<a href="https://pages.github.com/" target="_blank" rel="external">官方文档</a></p>
                </li>
                <li><p>配置和使用Github<br>以下教程主要参考beiyuu的<a href="http://beiyuu.com/github-pages/" target="_blank" rel="external">《使用Github Pages建独立博客》</a>写成。</p>
                </li>
                <li><p>配置SSH keys<br>我们如何让本地git项目与远程的github建立联系呢？用SSH keys。<br>打开Git Bash工具，执行以下操作</p>
                </li>
                <li><p>检查SSH keys的设置</p>
                </li>
            </ol>
        </blockquote>
            <figure class="highlight vbnet" data-lang=" vbnet"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br></pre></td><td class="code"><pre><span class="line">    首先我们需要检查你电脑上现有的ssh <span class="keyword">key</span>：</span><br><span class="line"></span><br><span class="line">$ cd ~/. ssh 检查本机的ssh密钥</span><br><span class="line"></span><br><span class="line">    如果提示：No such file <span class="keyword">or</span> directory 说明你是第一次使用git。</span><br><span class="line"></span><br><span class="line">    生成新的SSH <span class="keyword">Key</span>：</span><br><span class="line"></span><br><span class="line">$ ssh-keygen -t rsa -C <span class="string">"邮件地址@youremail.com"</span></span><br><span class="line">Generating <span class="keyword">public</span>/<span class="keyword">private</span> rsa <span class="keyword">key</span> pair.</span><br><span class="line">Enter file <span class="keyword">in</span> which <span class="keyword">to</span> save the <span class="keyword">key</span> </span><br><span class="line">(/Users/your_user_directory/.ssh/id_rsa):&lt;回车就好&gt;</span><br></pre></td></tr></table></figure>
            <p>注意1: 此处的邮箱地址，你可以输入自己的邮箱地址；<br>注意2: 此处的「-C」的是大写的「C」</p>
<pre><code>然后系统会要你输入密码：
</code></pre><p>Enter passphrase (empty for no passphrase):&lt;输入加密串&gt;<br>Enter same passphrase again:&lt;再次输入加密串&gt;</p>
            <p>在回车中会提示你输入一个密码，这个密码会在你提交项目时使用，如果为空的话提交项目时则不用输入。这个设置是防止别人往你的项目里提交内容。</p>
            <p>注意：输入密码的时候没有*字样的，你直接输入就可以了。</p>
            <p>最后看到这样的界面，就成功设置ssh key了：<br><figure class="highlight" data-lang=""><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br></pre></td><td class="code"><pre><span class="line"></span><br><span class="line"></span><br></pre></td></tr></table></figure></p>
            <blockquote>
                <ol>
                    <li>添加SSH Key到GitHub<br>在本机设置SSH Key之后，需要添加到GitHub上，以完成SSH链接的设置。</li>
                    <li>打开本地C:\Documents and Settings\Administrator.ssh\id_rsa.pub文件。此文件里面内容为刚才生成人密钥。如果看不到这个文件，你需要设置显示隐藏文件。准确的复制这个文件的内容，才能保证设置的成功。</li>
                    <li>登陆github系统。点击右上角的 Account Settings—&gt;SSH Public keys —&gt; add another public keys</li>
                    <li><p>把你本地生成的密钥复制到里面（key文本框中）， 点击 add key 就ok了</p>
                    </li>
                    <li><p>测试</p>
                    </li>
                </ol>
            </blockquote>
            <figure class="highlight coffeescript" data-lang=" coffeescript"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br><span class="line">14</span><br><span class="line">15</span><br><span class="line">16</span><br><span class="line">17</span><br><span class="line">18</span><br><span class="line">19</span><br><span class="line">20</span><br><span class="line">21</span><br><span class="line">22</span><br><span class="line">23</span><br><span class="line">24</span><br><span class="line">25</span><br><span class="line">26</span><br></pre></td><td class="code"><pre><span class="line">    可以输入下面的命令，看看设置是否成功，git<span class="property">@github</span>.com的部分不要修改：</span><br><span class="line"></span><br><span class="line">$ ssh -T git<span class="property">@github</span>.com</span><br><span class="line"></span><br><span class="line">    如果是下面的反馈：</span><br><span class="line"></span><br><span class="line">The authenticity <span class="keyword">of</span> host <span class="string">'github.com (207.97.227.239)'</span> can<span class="string">'t be established.</span><br><span class="line">RSA key fingerprint is 16:27:ac:a5:76:28:2d:36:63:1b:56:4d:eb:df:a6:48.</span><br><span class="line">Are you sure you want to continue connecting (yes/no)?</span><br><span class="line"></span><br><span class="line">    不要紧张，输入yes就好，然后会看到：</span><br><span class="line"></span><br><span class="line">Hi cnfeat! You'</span>ve successfully authenticated, </span><br><span class="line">but GitHub does <span class="keyword">not</span> provide shell access.</span><br><span class="line"></span><br><span class="line"></span><br><span class="line"></span><br><span class="line">    设置用户信息：</span><br><span class="line">    现在你已经可以通过SSH链接到GitHub了，还有一些个人信息需要完善的。</span><br><span class="line">    Git会根据用户的名字和邮箱来记录提交。</span><br><span class="line">    GitHub也是用这些信息来做权限的处理，输入下面的代码进行个人信息的设置，把名称和邮箱替换成你自己的，名字必须是你的真名，而不是GitHub的昵称。</span><br><span class="line"></span><br><span class="line">$ git config --<span class="built_in">global</span> user.name <span class="string">"cnfeat"</span><span class="regexp">//</span>用户名</span><br><span class="line">$ git config --<span class="built_in">global</span> user.email <span class="string">"cnfeat@gmail.com"</span><span class="regexp">//</span>填写自己的邮箱</span><br><span class="line"></span><br><span class="line">    SSH Key配置成功，本机已成功连接到github。</span><br></pre></td></tr></table></figure>
            <h1 id="Hexo_u535A_u5BA2"><a href="#Hexo_u535A_u5BA2" class="headerlink" title="Hexo博客"></a>Hexo博客</h1><blockquote>
            <p>Hexo<br>Hexo的作者是<a href="https://github.com/tommy351/hexo" target="_blank" rel="external">tommy351</a>，根据<a href="http://hexo.io/docs/index.html" target="_blank" rel="external">官方介绍</a>，Hexo是一个简单、快速、强大的博客发布工具，支持Markdown格式。hexo的主题列表 <a href="http://github.com/tommy351/hexo/wiki/Themes" target="_blank" rel="external">Hexo Themes</a>。 我比较喜欢 <a href="http://github.com/A-limon/pacman" target="_blank" rel="external">pacman</a> ， <a href="http://github.com/heroicyang/hexo-theme-modernist" target="_blank" rel="external">modernist</a> 、 <a href="http://github.com/DavidKk/Hexo.ishgo" target="_blank" rel="external">ishgo</a> ， <a href="http://github.com/raytaylorlin/hexo-theme-raytaylorism" target="_blank" rel="external">raytaylorism</a> 。 </p>
        </blockquote>
            <h2 id="u5B89_u88C5Hexo"><a href="#u5B89_u88C5Hexo" class="headerlink" title="安装Hexo"></a>安装Hexo</h2><p>打开Git Bash工具（前提确保Node.js已经安装，环境配置OK）</p>
<pre><code>$ npm install -g hexo
</code></pre><blockquote>
            <p>注释：</p>
        </blockquote>
            <p>执行命令：npm install -g hexo，报错如下：</p>
            <figure class="highlight stata" data-lang=" stata"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br><span class="line">14</span><br><span class="line">15</span><br><span class="line">16</span><br><span class="line">17</span><br><span class="line">18</span><br><span class="line">19</span><br><span class="line">20</span><br><span class="line">21</span><br><span class="line">22</span><br><span class="line">23</span><br><span class="line">24</span><br><span class="line">25</span><br><span class="line">26</span><br><span class="line">27</span><br><span class="line">28</span><br><span class="line">29</span><br><span class="line">30</span><br><span class="line">31</span><br></pre></td><td class="code"><pre><span class="line">npm <span class="keyword">ERR</span>! <span class="keyword">Error</span>: shasum check failed <span class="keyword">for</span> C:\Users\ADMINI~1\AppData\<span class="keyword">Local</span>\Temp\npm</span><br><span class="line">-30024-KDJHJzgP\registry.npmjs.org\hexo-<span class="keyword">cli</span>\-\hexo-<span class="keyword">cli</span>-0.1.6.tgz</span><br><span class="line">npm <span class="keyword">ERR</span>! Expected: 7dc3ab939d0889c4bed6a961605ff3e2d67f67a2</span><br><span class="line">npm <span class="keyword">ERR</span>! Actual:   41de7d67a9b764352eb07c49c32fc38dd7f479b9</span><br><span class="line">npm <span class="keyword">ERR</span>! From:     https:<span class="comment">//registry.npmjs.org/hexo-cli/-/hexo-cli-0.1.6.tgz</span></span><br><span class="line">npm <span class="keyword">ERR</span>!     at <span class="keyword">d</span>:\<span class="keyword">Program</span> Files\nodejs\node_modules\npm\node_modules\sha\index.</span><br><span class="line">js:38:8</span><br><span class="line">npm <span class="keyword">ERR</span>!     at ReadStream.&lt;anonymous&gt; (<span class="keyword">d</span>:\<span class="keyword">Program</span> Files\nodejs\node_modules\npm</span><br><span class="line">\node_modules\sha\index.js:85:7)</span><br><span class="line">npm <span class="keyword">ERR</span>!     at ReadStream.emit (events.js:117:20)</span><br><span class="line">npm <span class="keyword">ERR</span>!     at _stream_readable.js:943:16</span><br><span class="line">npm <span class="keyword">ERR</span>!     at process._tickCallback (node.js:419:13)</span><br><span class="line">npm <span class="keyword">ERR</span>! <span class="keyword">If</span> you need <span class="keyword">help</span>, you may <span class="keyword">report</span> this *entire* <span class="keyword">log</span>,</span><br><span class="line">npm <span class="keyword">ERR</span>! including the npm and node versions, at:</span><br><span class="line">npm <span class="keyword">ERR</span>!     &lt;http:<span class="comment">//github.com/npm/npm/issues&gt;</span></span><br><span class="line"></span><br><span class="line">npm <span class="keyword">ERR</span>! System Windows_NT 6.2.9200</span><br><span class="line">npm <span class="keyword">ERR</span>! command <span class="string">"d:\\Program Files\\nodejs\\node.exe"</span> "<span class="keyword">d</span>:\\<span class="keyword">Program</span> Files\\nodej</span><br><span class="line">s\\node_modules\\npm\\bin\\npm-<span class="keyword">cli</span>.js<span class="string">" "</span>install<span class="string">" "</span>-<span class="keyword">g</span><span class="string">" "</span>hexo"</span><br><span class="line">npm <span class="keyword">ERR</span>! cwd C:\Users\Administrator\Desktop</span><br><span class="line">npm <span class="keyword">ERR</span>! node -v v0.10.31</span><br><span class="line">npm <span class="keyword">ERR</span>! npm -v 1.4.23</span><br><span class="line">npm <span class="keyword">ERR</span>! registry <span class="keyword">error</span> parsing json</span><br><span class="line">npm <span class="keyword">ERR</span>!</span><br><span class="line">npm <span class="keyword">ERR</span>! Additional logging details can be found <span class="keyword">in</span>:</span><br><span class="line">npm <span class="keyword">ERR</span>!     C:\Users\Administrator\Desktop\npm-debug.<span class="literal">log</span></span><br><span class="line">npm <span class="keyword">ERR</span>! not ok code 0</span><br><span class="line"></span><br><span class="line">    莫非是因为被墙了？换国内镜像源试试。</span><br><span class="line">npm config <span class="keyword">set</span> registry=<span class="string">"http://registry.cnpmjs.org"</span>，</span><br><span class="line">    然后再次执行npm install -<span class="keyword">g</span> hexo，成功！</span><br></pre></td></tr></table></figure>
            <h2 id="u90E8_u7F72Hexo"><a href="#u90E8_u7F72Hexo" class="headerlink" title="部署Hexo"></a>部署Hexo</h2><figure class="highlight crystal" data-lang=" crystal"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br><span class="line">14</span><br><span class="line">15</span><br><span class="line">16</span><br><span class="line">17</span><br><span class="line">18</span><br><span class="line">19</span><br><span class="line">20</span><br><span class="line">21</span><br></pre></td><td class="code"><pre><span class="line">    在我的电脑中建立一个名字叫「<span class="constant">Hexo</span>」的文件夹，然后在此文件夹中右键打开<span class="constant">Git</span> <span class="constant">Bash</span>。</span><br><span class="line"></span><br><span class="line"><span class="variable">$ </span>hexo init</span><br><span class="line"></span><br><span class="line">    如果无法使用右击“<span class="constant">Git</span> <span class="constant">Bash</span>”，则可以切换到指定目录</span><br><span class="line"></span><br><span class="line">    <span class="constant">UUhike</span><span class="variable">@UUhike</span>-pc <span class="constant">MINGW64</span> ~</span><br><span class="line"><span class="variable">$ </span>cd <span class="symbol">j:</span>/github/hexo</span><br><span class="line">    <span class="constant">UUhike</span><span class="variable">@UUhike</span>-pc <span class="constant">MINGW64</span> /j/github/hexo</span><br><span class="line"></span><br><span class="line">    安装依赖包</span><br><span class="line"><span class="variable">$ </span>npm install</span><br><span class="line"></span><br><span class="line">    <span class="constant">Hexo</span>随后会自动在目标文件夹建立网站所需要的所有文件。</span><br><span class="line">    现在我们已经搭建起本地的hexo博客了，执行以下命令(在<span class="constant">H</span>:\hexo)，然后到浏览器输入<span class="symbol">localhost:</span><span class="number">4000</span>看看。</span><br><span class="line"></span><br><span class="line">    本地查看</span><br><span class="line"></span><br><span class="line"><span class="variable">$ </span>hexo generate <span class="comment">#生成静态页面至public目录（最终上传这个文件到GitHub）</span></span><br><span class="line"></span><br><span class="line"><span class="variable">$ </span>hexo server <span class="comment">#开启预览访问端口（默认端口4000，'ctrl + c'关闭server）</span></span><br></pre></td></tr></table></figure>
            <h2 id="u90E8_u7F72_u5230GitHub"><a href="#u90E8_u7F72_u5230GitHub" class="headerlink" title="部署到GitHub"></a>部署到GitHub</h2><figure class="highlight less" data-lang=" less"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br></pre></td><td class="code"><pre><span class="line"></span><br><span class="line">编辑<span class="attribute">E</span>:\hexo下的_config.yml，修改Deployment部分：</span><br><span class="line"></span><br><span class="line"># Deployment</span><br><span class="line">## <span class="attribute">Docs</span>: <span class="attribute">http</span>:<span class="comment">//hexo.io/docs/deployment.html</span></span><br><span class="line"><span class="attribute">deploy</span>:</span><br><span class="line">  <span class="attribute">type</span>: git</span><br><span class="line">    <span class="attribute">repository</span>: <span class="attribute">https</span>:<span class="comment">//github.com/luuman/luuman.github.io.git</span></span><br><span class="line">    <span class="attribute">branch</span>: master</span><br></pre></td></tr></table></figure>
            <blockquote>
                <p>注释：</p>
            </blockquote>
            <p>hexo d，执行该命令，报错：</p>
            <figure class="highlight sql" data-lang=" sql"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br><span class="line">14</span><br><span class="line">15</span><br><span class="line">16</span><br><span class="line">17</span><br><span class="line">18</span><br><span class="line">19</span><br><span class="line">20</span><br><span class="line">21</span><br><span class="line">22</span><br><span class="line">23</span><br><span class="line">24</span><br><span class="line">25</span><br><span class="line">26</span><br><span class="line">27</span><br><span class="line">28</span><br><span class="line">29</span><br><span class="line">30</span><br><span class="line">31</span><br><span class="line">32</span><br><span class="line">33</span><br><span class="line">34</span><br><span class="line">35</span><br></pre></td><td class="code"><pre><span class="line">ERROR Deployer not found: git</span><br><span class="line"></span><br><span class="line">执行命令：</span><br><span class="line">npm <span class="operator"><span class="keyword">install</span> hexo-deployer-git <span class="comment">--save</span></span><br><span class="line">再次执行hexo <span class="keyword">d</span>,报错：</span><br><span class="line"></span><br><span class="line">INFO  Deploying: git</span><br><span class="line">INFO  Clearing .deploy folder...</span><br><span class="line">INFO  Copying files <span class="keyword">from</span> <span class="keyword">public</span> folder...</span><br><span class="line"><span class="keyword">warning</span>: LF will be replaced <span class="keyword">by</span> CRLF <span class="keyword">in</span> <span class="number">2015</span>/<span class="number">05</span>/<span class="number">30</span>/hello-world/<span class="keyword">index</span>.html.</span><br><span class="line">The <span class="keyword">file</span> will have its original line endings <span class="keyword">in</span> your working <span class="keyword">directory</span>.</span><br><span class="line">......</span><br><span class="line">*** Please tell me who you <span class="keyword">are</span>.</span><br><span class="line"></span><br><span class="line">Run</span><br><span class="line"></span><br><span class="line">  git config <span class="comment">--global user.email "you@example.com"</span></span><br><span class="line">  git config <span class="comment">--global user.name "Your Name"</span></span><br><span class="line"></span><br><span class="line"><span class="keyword">to</span> <span class="keyword">set</span> your <span class="keyword">account</span><span class="string">'s default identity.</span><br><span class="line">Omit --global to set the identity only in this repository.</span><br><span class="line"></span><br><span class="line">fatal: unable to auto-detect email address (got '</span>Administrator@PC-<span class="number">201505290750.</span>(<span class="keyword">none</span>)<span class="string">')</span><br><span class="line">Username for '</span>https://github.com<span class="string">': voidking</span><br><span class="line">Password for '</span>https://voidking@github.com<span class="string">':</span><br><span class="line">error: src refspec master does not match any.</span><br><span class="line">error: failed to push some refs to '</span>https://github.com/voidking/voidking.github.io.git<span class="string">'</span><br><span class="line">FATAL Something'</span>s wrong. Maybe you can find the solution here: <span class="keyword">http</span>://hexo.io/docs/troubleshooting.html</span><br><span class="line"><span class="keyword">Error</span>: <span class="keyword">error</span>: src refspec <span class="keyword">master</span> does <span class="keyword">not</span> <span class="keyword">match</span> <span class="keyword">any</span>.</span><br><span class="line"><span class="keyword">error</span>: <span class="keyword">failed</span> <span class="keyword">to</span> push <span class="keyword">some</span> refs <span class="keyword">to</span> <span class="string">'https://github.com/voidking/voidking.github.io.git'</span></span><br><span class="line"></span><br><span class="line">    <span class="keyword">at</span> ChildProcess.&lt;anonymous&gt; (<span class="keyword">E</span>:\hexo\node_modules\hexo-deployer-git\node_modules\hexo-util\lib\spawn.js:<span class="number">42</span>:<span class="number">17</span>)</span><br><span class="line">    <span class="keyword">at</span> ChildProcess.emit (<span class="keyword">events</span>.js:<span class="number">98</span>:<span class="number">17</span>)</span><br><span class="line">    <span class="keyword">at</span> maybeClose (child_process.js:<span class="number">756</span>:<span class="number">16</span>)</span><br><span class="line">    <span class="keyword">at</span> Process.ChildProcess._handle.onexit (child_process.js:<span class="number">823</span>:<span class="number">5</span>)</span></span><br></pre></td></tr></table></figure>
            <p>hexo d，执行该命令，报错：</p>
            <h2 id="u590D_u5236cnfeat_u7684_u4E3B_u9898"><a href="#u590D_u5236cnfeat_u7684_u4E3B_u9898" class="headerlink" title="复制cnfeat的主题"></a>复制cnfeat的主题</h2><p>以下进入复制主题环节，如果那一步出现问题，或者修改后没有显示修改的结果，建议来来一个，再看看，可以解决很多问题。</p>
            <figure class="highlight crystal" data-lang=" crystal"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br></pre></td><td class="code"><pre><span class="line"><span class="variable">$ </span>hexo clean</span><br><span class="line"></span><br><span class="line"><span class="variable">$ </span>hexo generate <span class="comment">#生成静态页面至public目录（最终上传这个文件到GitHub）</span></span><br><span class="line"></span><br><span class="line"><span class="variable">$ </span>hexo server <span class="comment">#开启预览访问端口（默认端口4000，'ctrl + c'关闭server）</span></span><br><span class="line"></span><br><span class="line">建立了<span class="constant">Hexo</span>文件，复制我的主题了到themes文件夹中</span><br><span class="line">yilia</span><br><span class="line"><span class="variable">$ </span>git clone <span class="symbol">https:</span>/<span class="regexp">/github.com/litten</span><span class="regexp">/hexo-theme-yilia.git themes/yilia</span></span><br><span class="line">modernist</span><br><span class="line"><span class="variable">$ </span>git clone <span class="symbol">https:</span>/<span class="regexp">/github.com/heroicyang</span><span class="regexp">/hexo-theme-modernist.git themes/modernist</span></span><br><span class="line">jacman</span><br><span class="line"><span class="variable">$ </span>git clone <span class="symbol">https:</span>/<span class="regexp">/github.com/cnfeat</span><span class="regexp">/cnfeat.git themes/jacman</span></span><br></pre></td></tr></table></figure>
            <h2 id="u542F_u7528cnfeat_u7684_u4E3B_u9898"><a href="#u542F_u7528cnfeat_u7684_u4E3B_u9898" class="headerlink" title="启用cnfeat的主题"></a>启用cnfeat的主题</h2><p>修改Hexo目录下的config.yml配置文件中的theme属性，将其设置为jacman。同时请设置stylus属性中的compress值为true。<br>theme: jacman</p>
            <p>注意：Hexo有两个config.yml文件，一个在根目录，一个在theme下，此时修改的是在根目录下的。</p>
<pre><code>更新主题

    $ cd themes/jacman
    $ git pull
</code></pre><p>注意：为避免出错，请先备份你的_config.yml 文件后再升级<br>本地查看调试</p>
            <figure class="highlight stata" data-lang=" stata"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br><span class="line">14</span><br></pre></td><td class="code"><pre><span class="line">$ hexo <span class="keyword">g</span> #生成</span><br><span class="line">$ hexo s #启动本地服务，进行文章预览调试</span><br><span class="line"></span><br><span class="line">或者直接作用组合命令</span><br><span class="line"></span><br><span class="line">$ hexo deploy -<span class="keyword">g</span></span><br><span class="line">$ hexo server -<span class="keyword">g</span></span><br><span class="line"></span><br><span class="line">简写：</span><br><span class="line"></span><br><span class="line">hexo <span class="keyword">n</span> == hexo new</span><br><span class="line">hexo <span class="keyword">g</span> == hexo <span class="keyword">generate</span></span><br><span class="line">hexo s == hexo server</span><br><span class="line">hexo <span class="keyword">d</span> == hexo deploy</span><br></pre></td></tr></table></figure>
            <blockquote>
                <p>4、浏览器中查看效果</p>
            </blockquote>
            <p>浏览器输入<a href="http://localhost:4000" target="_blank" rel="external">http://localhost:4000</a> ，查看搭建效果。此后的每次变更_config.yml文件或者上传文件都可以先用此命令调试，非常好用，尤其是当你想调试出自己想要的主题时。</p>
            <p>#进阶篇：Hexo设置</p>
            <p>网站搭建完成后，就可以根据自己爱好来对Hexo生成的网站进行设置了，对整站的设置，只要修改项目目录的hexo/_config.yml就可以了，这是我的设置，可供参考。</p>
            <blockquote>
                <p>默认目录结构：</p>
            </blockquote>
            <figure class="highlight gradle" data-lang=" gradle"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br></pre></td><td class="code"><pre><span class="line">.</span><br><span class="line">├── .deploy</span><br><span class="line">├── <span class="keyword">public</span></span><br><span class="line">├── scaffolds</span><br><span class="line">├── scripts</span><br><span class="line">├── <span class="keyword">source</span></span><br><span class="line">|   ├── _drafts</span><br><span class="line">|   └── _posts</span><br><span class="line">├── themes</span><br><span class="line">├── _config.yml</span><br><span class="line">└── <span class="keyword">package</span>.json</span><br></pre></td></tr></table></figure>
            <blockquote>
                <p>hexo/_config.yml</p>
            </blockquote>
            <figure class="highlight avrasm" data-lang=" avrasm"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br><span class="line">14</span><br><span class="line">15</span><br><span class="line">16</span><br><span class="line">17</span><br><span class="line">18</span><br><span class="line">19</span><br><span class="line">20</span><br><span class="line">21</span><br><span class="line">22</span><br><span class="line">23</span><br><span class="line">24</span><br><span class="line">25</span><br><span class="line">26</span><br><span class="line">27</span><br><span class="line">28</span><br><span class="line">29</span><br><span class="line">30</span><br><span class="line">31</span><br><span class="line">32</span><br><span class="line">33</span><br><span class="line">34</span><br><span class="line">35</span><br><span class="line">36</span><br><span class="line">37</span><br><span class="line">38</span><br><span class="line">39</span><br><span class="line">40</span><br><span class="line">41</span><br><span class="line">42</span><br><span class="line">43</span><br><span class="line">44</span><br><span class="line">45</span><br><span class="line">46</span><br><span class="line">47</span><br><span class="line">48</span><br><span class="line">49</span><br><span class="line">50</span><br><span class="line">51</span><br><span class="line">52</span><br><span class="line">53</span><br><span class="line">54</span><br><span class="line">55</span><br><span class="line">56</span><br><span class="line">57</span><br><span class="line">58</span><br><span class="line">59</span><br><span class="line">60</span><br><span class="line">61</span><br><span class="line">62</span><br><span class="line">63</span><br><span class="line">64</span><br><span class="line">65</span><br><span class="line">66</span><br><span class="line">67</span><br><span class="line">68</span><br><span class="line">69</span><br><span class="line">70</span><br><span class="line">71</span><br><span class="line">72</span><br><span class="line">73</span><br><span class="line">74</span><br><span class="line">75</span><br><span class="line">76</span><br><span class="line">77</span><br><span class="line">78</span><br><span class="line">79</span><br><span class="line">80</span><br><span class="line">81</span><br><span class="line">82</span><br><span class="line">83</span><br><span class="line">84</span><br><span class="line">85</span><br></pre></td><td class="code"><pre><span class="line"><span class="preprocessor"># Hexo Configuration</span></span><br><span class="line"><span class="preprocessor">## Docs: http://hexo.io/docs/configuration.html</span></span><br><span class="line"><span class="preprocessor">## Source: https://github.com/hexojs/hexo/</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Site #整站的基本信息</span></span><br><span class="line"><span class="label">title:</span> <span class="number">1000</span> words a Day <span class="preprocessor">#网站标题</span></span><br><span class="line"><span class="label">subtitle:</span> Writing <span class="number">1000</span> Words a Day Changes My Life <span class="preprocessor">#网站副标题</span></span><br><span class="line"><span class="label">description:</span> 学习总结 思考感悟 知识管理 <span class="preprocessor">#网站描述</span></span><br><span class="line"><span class="label">author:</span>  cnFeat <span class="preprocessor">#网站作者，在下方显示</span></span><br><span class="line"><span class="label">email:</span> cnFeat@gmail.com <span class="preprocessor">#联系邮箱</span></span><br><span class="line"><span class="label">language:</span> <span class="built_in">zh</span>-CN <span class="preprocessor">#主题实际的文件名称</span></span><br><span class="line"><span class="label">timezone:</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># URL #这项暂不配置，绑定域名后，欲创建sitemap.xml需要配置该项</span></span><br><span class="line"><span class="preprocessor">## If your site is put in a subdirectory, set url as 'http://yoursite.com/child' and root as '/child/'</span></span><br><span class="line"><span class="label">url:</span> http://yoursite.com</span><br><span class="line"><span class="label">root:</span> /</span><br><span class="line"><span class="label">permalink:</span> :year/:month/:day/:title/</span><br><span class="line"><span class="label">permalink_defaults:</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Directory</span></span><br><span class="line"><span class="label">source_dir:</span> source</span><br><span class="line"><span class="label">public_dir:</span> public</span><br><span class="line"><span class="label">tag_dir:</span> tags</span><br><span class="line"><span class="label">archive_dir:</span> archives</span><br><span class="line"><span class="label">category_dir:</span> categories</span><br><span class="line"><span class="label">code_dir:</span> downloads/code</span><br><span class="line"><span class="label">i18n_dir:</span> :lang</span><br><span class="line"><span class="label">skip_render:</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Writing 文章布局、写作格式的定义，不修改</span></span><br><span class="line"><span class="label">new_post_name:</span> :title.md <span class="preprocessor"># File name of new posts</span></span><br><span class="line"><span class="label">default_layout:</span> post</span><br><span class="line"><span class="label">titlecase:</span> false <span class="preprocessor"># Transform title into titlecase</span></span><br><span class="line"><span class="label">external_link:</span> true <span class="preprocessor"># Open external links in new tab</span></span><br><span class="line"><span class="label">filename_case:</span> <span class="number">0</span></span><br><span class="line"><span class="label">render_drafts:</span> false</span><br><span class="line"><span class="label">post_asset_folder:</span> false</span><br><span class="line"><span class="label">relative_link:</span> false</span><br><span class="line"><span class="label">future:</span> true</span><br><span class="line"><span class="label">highlight:</span></span><br><span class="line">  enable: true</span><br><span class="line">  line_number: true</span><br><span class="line">  auto_detect: true</span><br><span class="line">  tab_replace:</span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Category &amp; Tag</span></span><br><span class="line"><span class="label">default_category:</span> uncategorized</span><br><span class="line"><span class="label">category_map:</span></span><br><span class="line"><span class="label">tag_map:</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Date / Time format 日期格式，不修改</span></span><br><span class="line"><span class="preprocessor">## Hexo uses Moment.js to parse and display date</span></span><br><span class="line"><span class="preprocessor">## You can customize the date format as defined in</span></span><br><span class="line"><span class="preprocessor">## http://momentjs.com/docs/#/displaying/format/</span></span><br><span class="line"><span class="label">date_format:</span> YYYY-MM-DD</span><br><span class="line"><span class="label">time_format:</span> HH:mm:ss</span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Pagination 每页显示文章数，可以自定义，我将10改成了5</span></span><br><span class="line"><span class="preprocessor">## Set per_page to 0 to disable pagination</span></span><br><span class="line"><span class="label">per_page:</span> <span class="number">5</span></span><br><span class="line"><span class="label">pagination_dir:</span> page</span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Disqus Disqus插件，我们会替换成“多说”，不修改</span></span><br><span class="line"><span class="label">disqus_shortname:</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Extensions</span></span><br><span class="line"><span class="preprocessor">## Plugins: http://hexo.io/plugins/</span></span><br><span class="line"><span class="preprocessor">## Themes: http://hexo.io/themes/</span></span><br><span class="line"><span class="label">theme:</span> spfk</span><br><span class="line"></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># 自动生成sitemap</span></span><br><span class="line"><span class="label">sitemap:</span></span><br><span class="line"><span class="label">path:</span> sitemap.xml</span><br><span class="line"><span class="label">baidusitemap:</span></span><br><span class="line"><span class="label">path:</span> baidusitemap.xml</span><br><span class="line"></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Deployment 站点部署到github要配置，上一节中已经讲过</span></span><br><span class="line"><span class="preprocessor">## Docs: http://zespia.tw/hexo/docs/deploy.html</span></span><br><span class="line"><span class="label">deploy:</span></span><br><span class="line">  type: git</span><br><span class="line">  repository: git@github.com:luuman/luuman.github.io.git</span><br><span class="line">  branch: master</span><br></pre></td></tr></table></figure>
            <p>修改局部页面</p>
            <p>页面展现的全部逻辑都在每个主题中控制，源代码在hexo\themes\主题名称\中：</p>
            <blockquote>
                <p>hexo\themes\</p>
            </blockquote>
            <figure class="highlight glsl" data-lang=" glsl"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br><span class="line">14</span><br><span class="line">15</span><br><span class="line">16</span><br><span class="line">17</span><br></pre></td><td class="code"><pre><span class="line">├── languages  <span class="preprocessor">#多语言</span></span><br><span class="line">|   ├── <span class="keyword">default</span>.yml<span class="preprocessor">#默认语言</span></span><br><span class="line">|   └── zh-CN.yml  <span class="preprocessor">#中文语言</span></span><br><span class="line">├── <span class="keyword">layout</span> <span class="preprocessor">#布局，根目录下的*.ejs文件是对主页，分页，存档等的控制</span></span><br><span class="line">|   ├── _partial   <span class="preprocessor">#局部的布局，此目录下的*.ejs是对头尾等局部的控制</span></span><br><span class="line">|   └── _widget<span class="preprocessor">#小挂件的布局，页面下方小挂件的控制</span></span><br><span class="line">├── source <span class="preprocessor">#源码</span></span><br><span class="line">|   ├── css<span class="preprocessor">#css源码 </span></span><br><span class="line">|   |   ├── _base  <span class="preprocessor">#*.styl基础css</span></span><br><span class="line">|   |   ├── _partial   <span class="preprocessor">#*.styl局部css</span></span><br><span class="line">|   |   ├── fonts  <span class="preprocessor">#字体</span></span><br><span class="line">|   |   ├── images <span class="preprocessor">#图片</span></span><br><span class="line">|   |   └── style.styl <span class="preprocessor">#*.styl引入需要的css源码</span></span><br><span class="line">|   ├── fancybox   <span class="preprocessor">#fancybox效果源码</span></span><br><span class="line">|   └── js <span class="preprocessor">#javascript源代码</span></span><br><span class="line">├── _config.yml<span class="preprocessor">#主题配置文件</span></span><br><span class="line">└── README.md  <span class="preprocessor">#用GitHub的都知道</span></span><br></pre></td></tr></table></figure>
            <p>主题文档的配置</p>
            <blockquote>
                <p>hexo\themes/_config.yml</p>
            </blockquote>
            <figure class="highlight vala" data-lang=" vala"><table><tr><td class="gutter"><pre><span class="line">1</span><br><span class="line">2</span><br><span class="line">3</span><br><span class="line">4</span><br><span class="line">5</span><br><span class="line">6</span><br><span class="line">7</span><br><span class="line">8</span><br><span class="line">9</span><br><span class="line">10</span><br><span class="line">11</span><br><span class="line">12</span><br><span class="line">13</span><br><span class="line">14</span><br><span class="line">15</span><br><span class="line">16</span><br><span class="line">17</span><br><span class="line">18</span><br><span class="line">19</span><br><span class="line">20</span><br><span class="line">21</span><br><span class="line">22</span><br><span class="line">23</span><br><span class="line">24</span><br><span class="line">25</span><br><span class="line">26</span><br><span class="line">27</span><br><span class="line">28</span><br><span class="line">29</span><br><span class="line">30</span><br><span class="line">31</span><br><span class="line">32</span><br><span class="line">33</span><br><span class="line">34</span><br><span class="line">35</span><br><span class="line">36</span><br><span class="line">37</span><br><span class="line">38</span><br><span class="line">39</span><br><span class="line">40</span><br><span class="line">41</span><br><span class="line">42</span><br><span class="line">43</span><br><span class="line">44</span><br><span class="line">45</span><br><span class="line">46</span><br><span class="line">47</span><br><span class="line">48</span><br><span class="line">49</span><br><span class="line">50</span><br><span class="line">51</span><br><span class="line">52</span><br><span class="line">53</span><br><span class="line">54</span><br><span class="line">55</span><br><span class="line">56</span><br><span class="line">57</span><br></pre></td><td class="code"><pre><span class="line"><span class="preprocessor"># Header</span></span><br><span class="line">menu:</span><br><span class="line">  主页: /</span><br><span class="line">  所有文章: /archives</span><br><span class="line">  # 随笔: /tags/随笔</span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># SubNav</span></span><br><span class="line">subnav:</span><br><span class="line">  github: <span class="string">"#"</span></span><br><span class="line">  weibo: <span class="string">"#"</span></span><br><span class="line">  rss: <span class="string">"#"</span></span><br><span class="line">  zhihu: <span class="string">"#"</span></span><br><span class="line">  #douban: <span class="string">"#"</span></span><br><span class="line">  #mail: <span class="string">"#"</span></span><br><span class="line">  #facebook: <span class="string">"#"</span></span><br><span class="line">  #google: <span class="string">"#"</span></span><br><span class="line">  #twitter: <span class="string">"#"</span></span><br><span class="line">  #linkedin: <span class="string">"#"</span></span><br><span class="line"></span><br><span class="line">rss: /atom.xml</span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Content</span></span><br><span class="line">excerpt_link: more</span><br><span class="line">fancybox: <span class="literal">true</span></span><br><span class="line">mathjax: <span class="literal">true</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor"># Miscellaneous</span></span><br><span class="line">google_analytics: <span class="string">''</span></span><br><span class="line">favicon: /favicon.png</span><br><span class="line"></span><br><span class="line"><span class="preprocessor">#你的头像url</span></span><br><span class="line">avatar: <span class="string">""</span></span><br><span class="line"><span class="preprocessor">#是否开启分享</span></span><br><span class="line">share: <span class="literal">true</span></span><br><span class="line"><span class="preprocessor">#是否开启多说评论，填写你在多说申请的项目名称 duoshuo: duoshuo-key（http://duoshuo-key.duoshuo.com/）</span></span><br><span class="line"><span class="preprocessor">#若使用disqus，请在博客config文件中填写disqus_shortname，并关闭多说评论</span></span><br><span class="line">duoshuo: <span class="literal">true</span></span><br><span class="line"><span class="preprocessor">#是否开启云标签</span></span><br><span class="line">tagcloud: <span class="literal">true</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor">#是否开启友情链接</span></span><br><span class="line"><span class="preprocessor">#不开启——</span></span><br><span class="line"><span class="preprocessor">#friends: false</span></span><br><span class="line"><span class="preprocessor">#开启——</span></span><br><span class="line">friends:</span><br><span class="line">  奥巴马的博客: http:<span class="comment">//localhost:4000/</span></span><br><span class="line">  卡卡的美丽传说: http:<span class="comment">//localhost:4000/</span></span><br><span class="line">  本泽马的博客: http:<span class="comment">//localhost:4000/</span></span><br><span class="line">  吉格斯的博客: http:<span class="comment">//localhost:4000/</span></span><br><span class="line">  习大大大不同: http:<span class="comment">//localhost:4000/</span></span><br><span class="line">  托蒂的博客: http:<span class="comment">//localhost:4000/</span></span><br><span class="line"></span><br><span class="line"><span class="preprocessor">#是否开启“关于我”。</span></span><br><span class="line"><span class="preprocessor">#不开启——</span></span><br><span class="line"><span class="preprocessor">#aboutme: false</span></span><br><span class="line"><span class="preprocessor">#开启——</span></span><br><span class="line">aboutme: 我是谁，我从哪里来，我到哪里去？我就是我，是颜色不一样的吃货…</span><br></pre></td></tr></table></figure>
            <h1 id="u53C2_u8003_u8D44_u6599_uFF1A"><a href="#u53C2_u8003_u8D44_u6599_uFF1A" class="headerlink" title="参考资料："></a>参考资料：</h1><p><a href="http://ibruce.info/2013/11/22/hexo-your-blog/" target="_blank" rel="external">hexo你的博客</a><br><a href="http://www.jianshu.com/p/05289a4bc8b2#" target="_blank" rel="external">如何搭建一个独立博客——简明Github Pages与Hexo教程</a><br><a href="http://luuman.github.io/2015/12/25/Hexo/" target="_blank" rel="external">Hexo的使用介绍</a><br><a href="http://luuman.github.io/2015/12/27/Hexo-plug/" target="_blank" rel="external">Hexo插件安装</a></p>


        </div>


    </div>


    <div class="copyright">
        <p><span>本文标题:</span><a href="/2016/01/16/hello-world/">前端知识体系</a></p>
        <p><span>文章作者:</span><a href="/" title="访问 John Doe 的个人博客">John Doe</a></p>
        <p><span>发布时间:</span>2016年01月16日 - 13时58分</p>
        <p><span>最后更新:</span>2016年03月13日 - 18时37分</p>
        <p>
            <span>原始链接:</span><a class="post-url" href="/2016/01/16/hello-world/" title="前端知识体系">http://yoursite.com/2016/01/16/hello-world/</a>
            <span class="copy-path" data-clipboard-text="原文: http://yoursite.com/2016/01/16/hello-world/　　作者: John Doe"
                  title="点击复制文章链接"><i class="fa fa-clipboard"></i></span>
            <script src="/js/clipboard.min.js"></script>
            <script> var clipboard = new Clipboard('.copy-path'); </script>
        </p>
        <p>
            <span>许可协议:</span><i class="fa fa-creative-commons"></i> <a rel="license"
                                                                        href="http://creativecommons.org/licenses/by-nc-sa/3.0/cn/"
                                                                        title="中国大陆 (CC BY-NC-SA 3.0 CN)"
                                                                        target="_blank">"署名-非商用-相同方式共享 3.0"</a>
            转载请保留原文链接及作者。
        </p>
    </div>


    <nav id="article-nav">


        <a href="/2016/01/16/mytest/" id="article-nav-older" class="article-nav-link-wrap">
            <div class="article-nav-title">前端知识体系</div>
            <strong class="article-nav-caption">></strong>
        </a>

    </nav>


</article>

<div id="toc" class="toc-article">
    <strong class="toc-title">文章目录</strong>
    <ol class="toc"><li class="toc-item toc-level-1"><a class="toc-link" href="#u6982_u51B5"><span class="toc-number">1.</span> <span class="toc-text">概况</span></a></li><li class="toc-item toc-level-1"><a class="toc-link" href="#u5B89_u88C5_u51C6_u5907"><span class="toc-number">2.</span> <span class="toc-text">安装准备</span></a><ol class="toc-child"><li class="toc-item toc-level-2"><a class="toc-link" href="#u5B89_u88C5Node"><span class="toc-number">2.1.</span> <span class="toc-text">安装Node</span></a></li><li class="toc-item toc-level-2"><a class="toc-link" href="#u5B89_u88C5Git"><span class="toc-number">2.2.</span> <span class="toc-text">安装Git</span></a></li><li class="toc-item toc-level-2"><a class="toc-link" href="#u5B89_u88C5Sublime"><span class="toc-number">2.3.</span> <span class="toc-text">安装Sublime</span></a></li></ol></li><li class="toc-item toc-level-1"><a class="toc-link" href="#GitHub_u6CE8_u518C_u4E0E_u914D_u7F6E"><span class="toc-number">3.</span> <span class="toc-text">GitHub注册与配置</span></a></li><li class="toc-item toc-level-1"><a class="toc-link" href="#Hexo_u535A_u5BA2"><span class="toc-number">4.</span> <span class="toc-text">Hexo博客</span></a><ol class="toc-child"><li class="toc-item toc-level-2"><a class="toc-link" href="#u5B89_u88C5Hexo"><span class="toc-number">4.1.</span> <span class="toc-text">安装Hexo</span></a></li><li class="toc-item toc-level-2"><a class="toc-link" href="#u90E8_u7F72Hexo"><span class="toc-number">4.2.</span> <span class="toc-text">部署Hexo</span></a></li><li class="toc-item toc-level-2"><a class="toc-link" href="#u90E8_u7F72_u5230GitHub"><span class="toc-number">4.3.</span> <span class="toc-text">部署到GitHub</span></a></li><li class="toc-item toc-level-2"><a class="toc-link" href="#u590D_u5236cnfeat_u7684_u4E3B_u9898"><span class="toc-number">4.4.</span> <span class="toc-text">复制cnfeat的主题</span></a></li><li class="toc-item toc-level-2"><a class="toc-link" href="#u542F_u7528cnfeat_u7684_u4E3B_u9898"><span class="toc-number">4.5.</span> <span class="toc-text">启用cnfeat的主题</span></a></li></ol></li><li class="toc-item toc-level-1"><a class="toc-link" href="#u53C2_u8003_u8D44_u6599_uFF1A"><span class="toc-number">5.</span> <span class="toc-text">参考资料：</span></a></li></ol>
</div>
<style>
    .left-col .switch-btn {
        display: none;
    }

    .left-col .switch-area {
        display: none;
    }
</style>

<input type="button" id="tocButton" value="隐藏目录" title="点击按钮隐藏或者显示文章目录">

<script src="http://7.url.cn/edu/jslib/comb/require-2.1.6,jquery-1.9.1.min.js"></script>
<script>
    var valueHide = "隐藏目录";
    var valueShow = "显示目录";

    if ($(".left-col").is(":hidden")) {
        $("#tocButton").attr("value", valueShow);
    }

    $("#tocButton").click(function () {
        if ($("#toc").is(":hidden")) {
            $("#tocButton").attr("value", valueHide);
            $("#toc").slideDown(320);
            $(".switch-btn, .switch-area").fadeOut(300);
        }
        else {
            $("#tocButton").attr("value", valueShow);
            $("#toc").slideUp(350);
            $(".switch-btn, .switch-area").fadeIn(500);
        }
    })

    if ($(".toc").length < 1) {
        $("#toc, #tocButton").hide();
        $(".switch-btn, .switch-area").show();
    }
</script>


<div class="bdsharebuttonbox">
    <a href="#" class="fx fa-weibo bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
    <a href="#" class="fx fa-weixin bds_weixin" data-cmd="weixin" title="分享到微信"></a>
    <a href="#" class="fx fa-qq bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
    <a href="#" class="fx fa-facebook-official bds_fbook" data-cmd="fbook" title="分享到Facebook"></a>
    <a href="#" class="fx fa-twitter bds_twi" data-cmd="twi" title="分享到Twitter"></a>
    <a href="#" class="fx fa-linkedin bds_linkedin" data-cmd="linkedin" title="分享到linkedin"></a>
    <a href="#" class="fx fa-files-o bds_copy" data-cmd="copy" title="分享到复制网址"></a>
</div>
<script>window._bd_share_config = {
    "common": {
        "bdSnsKey": {},
        "bdText": "",
        "bdMini": "2",
        "bdMiniList": false,
        "bdPic": "",
        "bdStyle": "2",
        "bdSize": "24"
    }, "share": {}
};
with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>


<div class="duoshuo" id="comments">
    <!-- 多说评论框 start -->
    <div class="ds-thread" data-thread-key="2016/01/16/hello-world/" data-title="前端知识体系"
         data-url="http://yoursite.com/2016/01/16/hello-world/"></div>
    <!-- 多说评论框 end -->
    <!-- 多说公共JS代码 start (一个网页只需插入一次) -->
    <script type="text/javascript">
        var duoshuoQuery = {short_name: "luums"};
        (function () {
            var ds = document.createElement('script');
            ds.type = 'text/javascript';
            ds.async = true;
            ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
            ds.charset = 'UTF-8';
            (document.getElementsByTagName('head')[0]
            || document.getElementsByTagName('body')[0]).appendChild(ds);
        })();
    </script>
    <!-- 多说公共JS代码 end -->
</div>


<div class="scroll" id="post-nav-button">

    <a href="/" title="回到主页"><i class="fa fa-home"></i></a>

    <a title="文章列表"><i class="fa fa-bars"></i><i class="fa fa-times"></i></a>

    <a href="/2016/01/16/mytest/" title="下一篇: 前端知识体系">
        <i class="fa fa-angle-right"></i>
    </a>

</div>
<ul class="post-list">
    <li class="post-list-item"><a class="post-list-link" href="/2016/01/16/hello-world/">前端知识体系</a></li>
    <li class="post-list-item"><a class="post-list-link" href="/2016/01/16/mytest/">前端知识体系</a></li>
</ul>
<script src="http://7.url.cn/edu/jslib/comb/require-2.1.6,jquery-1.9.1.min.js"></script>
<script>
    $(".post-list").addClass("toc-article");
    $(".post-list-item a").attr("target", "_blank");
    $("#post-nav-button > a:nth-child(2)").click(function () {
        $(".fa-bars, .fa-times").toggle();
        $(".post-list").toggle(300);
        if ($(".toc").length > 0) {
            $("#toc, #tocButton").toggle(200, function () {
                if ($(".switch-area").is(":visible")) {
                    $("#toc, .switch-btn, .switch-area").toggle();
                    $("#tocButton").attr("value", valueHide);
                }
            })
        }
        else {
            $(".switch-btn, .switch-area").fadeToggle(300);
        }
    })
</script>


<script>

</script>
</@html>