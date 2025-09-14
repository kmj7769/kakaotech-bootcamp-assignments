package assignment1.item;

// 게임 BGM(텍스트 형식)을 재생하는 클래스
public class BackGroundMusicPlay implements Runnable {

    // 음악 재생 상태를 설정하기 위한 속성
    private boolean isRunning = true;

    // 재생 중(재생 대기 중)인 음악
    private String backGroundMusic = "Peaceful Theme: Game Start BGM";

    // 음악 재생 종료
    public void stopMusic() {
        isRunning = false;
    }

    // 재생 중인 음악 변경
    public void setMusic(String backGroundMusic) {
        this.backGroundMusic = backGroundMusic;
    }

    // 스레드 실행 메서드 (Runnable 구현)
    // 일정 시간마다 BGM 재생 텍스트 출력
    @Override
    public void run() {
        while (isRunning) {
            System.out.println(backGroundMusic + " is playing...");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
