package clients.asteroids;

import DTO.AccountInfo;
import DTO.AppState;
import clients.asteroids.messages.GameState;
import clients.asteroids.messages.PackageMobileSetAttributes;
import clients.asteroids.messages.PackageShipInfo;
import clients.asteroids.messages.PackageShipMobile;

public class ClusterCommunicationController {


    public void setGameState(GameState gameState) {
        AccountInfo.getAccount().setGameState(gameState);
    }

    public void managePackageShipMobile(PackageShipMobile message) {
        if(message.getMessage() instanceof  GameState){
            AccountInfo.getAccount().setGameState((GameState) message.getMessage());
        }else if(message.getMessage() instanceof PackageShipInfo){

        }
    }

    public void setAttributes(PackageMobileSetAttributes attributes) {
        AppState.getAppState().setIp(attributes.getIp());
        AccountInfo.getAccount().setShipId(attributes.getId());
        AccountInfo.getAccount().setMobilMaster(attributes.isMaster());
        AccountInfo.getAccount().setTeam(attributes.getTeam());

    }

    public void updateTeamAndLife(PackageShipInfo message){
        AccountInfo.getAccount().setTeam(message.getTeam());
        AccountInfo.getAccount().setLifeScore(message.getLife());

    }
}
