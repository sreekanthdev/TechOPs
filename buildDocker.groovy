def call(){
  //def branch = env.BRANCH_NAME.toLowerCase()
  def branch = env.BRANCH_NAME.toLowerCase()
  def image = env.IMAGE_NAME ?: "${env.JOB_NAME}".split('/')[0]
  def tag = currentBuild.id
  def channel = '#builds'
  sh " docker system prune -f"
  print "buildDocker(${branch}/${image}:${tag})"
  docker.build("${branch}/${image}:${tag}")
  true
  
  CInotifyslack("${channel}","${branch}" || *${env.IMAGE_NAME}* || Build-${currentBuild.id} || #${env.STAGE_NAME}# || *${currentBuild.currentResult}*","graeen", "${barnch}")
  
  
